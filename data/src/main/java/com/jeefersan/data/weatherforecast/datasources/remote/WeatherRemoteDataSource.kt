package com.jeefersan.data.weatherforecast.datasources.remote

import com.jeefersan.domain.*
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 28-4-20.
 */

interface WeatherRemoteDataSource {
    suspend fun getWeatherForecast(coordinates: Coordinates) : Result<WeatherForecast>

    suspend fun getCurrentWeather(coordinates: Coordinates) : Result<CurrentWeather>

    suspend fun getForecast(coordinates: Coordinates) : Result<Forecast>


}