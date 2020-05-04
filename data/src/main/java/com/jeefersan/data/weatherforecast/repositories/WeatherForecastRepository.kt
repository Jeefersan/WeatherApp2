package com.jeefersan.data.weatherforecast.repositories

import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 28-4-20.
 */

interface WeatherForecastRepository {

    suspend fun insertOrUpdate(weatherForecast: WeatherForecast) : Result<Unit>

    suspend fun getWeatherForecastFromRemote(location: Location): Result<WeatherForecast>

    suspend fun getWeatherForecastByIdFromLocal(id: Long): Result<WeatherForecast>

}