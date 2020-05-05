package com.jeefersan.data.unused.currentweather.datasources.remote

import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result
import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.data.unused.currentweather.datasources.remote.util.mapToWeather

/**
 * Created by JeeferSan on 21-4-20.
 */
class CurrentWeatherRemoteDataSourceImpl(private val weatherApi: WeatherApiService) :
    CurrentWeatherRemoteDataSource {

    override suspend fun getWeatherByCoordinates(lat: Double, long: Double): Result<CurrentWeather> =
        try {
            val response = weatherApi.getWeatherByCoordinates(lat, long)
            Result.Success(
                response.mapToWeather()
            )
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

}