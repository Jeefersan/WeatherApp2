package com.jeefersan.weatherapp.framework.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.location.datasources.LocationProvider
import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.weatherapp.framework.configs.ConfigsManager

/**
 * Created by JeeferSan on 13-5-20.
 */
class MyWorkerFactory(private val weatherApiService: WeatherApiService,
private val favoritesDao: FavoritesDao,
private val configsManager: ConfigsManager,
private val locationProvider: LocationProvider) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            DailyNotificationWorker::class.java.name ->
                DailyNotificationWorker(appContext, workerParameters, weatherApiService, favoritesDao, configsManager)
            RainNotificationWorker::class.java.name ->
                RainNotificationWorker(appContext, workerParameters, weatherApiService, locationProvider)
            else ->
                null
        }

    }
}