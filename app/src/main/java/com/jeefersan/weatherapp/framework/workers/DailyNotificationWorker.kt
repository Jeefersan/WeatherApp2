package com.jeefersan.weatherapp.framework.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.weatherapp.framework.configs.ConfigsManager
import com.jeefersan.weatherapp.misc.showDailyNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.SocketException

/**
 * Created by JeeferSan on 13-5-20.
 */
class DailyNotificationWorker(
    context: Context,
    params: WorkerParameters,
    private val weatherApi: WeatherApiService,
    private val favoritesDao: FavoritesDao,
    private val configsManager: ConfigsManager
) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        val id = configsManager.getIdForDailyNotification()
        if (id == -1) {
            return@withContext Result.success()
        }

        val favorite = favoritesDao.getFavoriteById(id)

        Log.d("DailyNotificationWorker", "favorite is ${favorite.cityName}")
        try {
            val daily = weatherApi.getForecastByCoordinates(
                favorite.latitude,
                favorite.longitude
            ).daily.first()

            applicationContext.showDailyNotification(daily, favorite.cityName, favorite.id)

            Result.success()

        } catch (e: Exception) {
            if (runAttemptCount > MAX_NUMBER_OF_RETRY) {
                Log.d(
                    "DailyNotificationWorker",
                    "runAttemptCount=$runAttemptCount, returning with Result.success"
                )
                return@withContext Result.success()
            }
            when (e.cause) {
                is SocketException -> Result.retry()
                else -> Result.failure()
            }
        } finally {
            delay(120000L)
            val notificationTime = configsManager.getTimeForDailyNotification()!!
            WorkManager.getInstance(applicationContext).enqueueUniqueWork(
                DAILY_NOTIFICATION_TAG, ExistingWorkPolicy.REPLACE,
                getDailyNotificationRequest(notificationTime)
            )

        }
    }
}

//todo: ??