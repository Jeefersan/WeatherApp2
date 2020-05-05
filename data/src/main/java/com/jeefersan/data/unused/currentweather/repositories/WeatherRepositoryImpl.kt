package com.jeefersan.data.unused.currentweather.repositories

import com.jeefersan.data.unused.location.datasources.LocationProvider
import com.jeefersan.data.unused.currentweather.datasources.local.WeatherLocalDataSource
import com.jeefersan.data.unused.currentweather.datasources.remote.CurrentWeatherRemoteDataSource
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 22-4-20.
 */

class WeatherRepositoryImpl(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
    private val locationProvider: LocationProvider
) : WeatherRepository {

    override suspend fun getCurrentWeather(): Result<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentWeatherFromCoordinates(coordinates: Coordinates): Result<CurrentWeather> =
        try {
            currentWeatherRemoteDataSource.getWeatherByCoordinates(coordinates.lat, coordinates.long)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


    override suspend fun getMultipleCurrentWeathers(locations: List<Location>): Result<List<CurrentWeather>> {
        TODO("Not yet implemented")
    }
}