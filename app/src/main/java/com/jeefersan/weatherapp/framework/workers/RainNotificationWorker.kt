package com.jeefersan.weatherapp.framework.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jeefersan.data.location.datasources.LocationProvider
import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.weatherapp.misc.showRainNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent


/**
 * Created by JeeferSan on 13-5-20.
 */
class RainNotificationWorker(
    context: Context,
    parameters: WorkerParameters,
    private val weatherApi: WeatherApiService,
    private val locationProvider: LocationProvider
) :
    CoroutineWorker(context, parameters), KoinComponent {

    @ExperimentalCoroutinesApi
    override suspend fun doWork(): Result {
        var cityName = "Current Location"

        locationProvider.getCurrentLocation()
            .onStart { Log.d("RainNotificationWorker", "onStart") }
            .map {
                if (it is com.jeefersan.util.Result.Success) {
                    cityName = it.data.cityName
                    return@map com.jeefersan.util.Result.Success(
                        it.data
                    )
                } else return@map it
            }
            .flowOn(Dispatchers.Main)
            .filter { it is com.jeefersan.util.Result.Success }
            .map { result ->
                val location = (result as com.jeefersan.util.Result.Success).data
                val hourlyForecast = withContext(Dispatchers.IO) {
                    weatherApi.getForecastByCoordinates(
                        location.coordinates.lat,
                        location.coordinates.long
                    )
                }
                return@map hourlyForecast.hourly
            }
            .flowOn(Dispatchers.IO)
            .map { list ->
                val result = list.take(12).filter { hourly ->
                    hourly.weather.first().description.contains("rain", true)
                }
                result
            }
            .flowOn(Dispatchers.Default)
            .onCompletion { Log.d("RainNotificationWorker", "onCompletion") }
            .collect { list ->
                if (list.isEmpty()) {
                    return@collect
                } else {
                    applicationContext.showRainNotification(list, cityName)
                    return@collect
                }
            }
        return Result.success()

    }
}






