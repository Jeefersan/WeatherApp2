package com.jeefersan.data.weatherforecast.datasources.local

import com.jeefersan.data.weatherforecast.datasources.local.db.models.WeatherForecastEntity
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 1-5-20.
 */
interface WeatherForecastLocalDataSource {

    suspend fun insertWeatherForecast(weatherForecast: WeatherForecast) : Result<Unit>
    suspend fun getWeatherForecastById(id: Long): Result<WeatherForecast>

}