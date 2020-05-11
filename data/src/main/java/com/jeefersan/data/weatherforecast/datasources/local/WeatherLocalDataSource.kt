package com.jeefersan.data.weatherforecast.datasources.local

import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.Forecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 9-5-20.
 */
interface WeatherLocalDataSource {
    suspend fun getCurrentWeatherById(id: Int) : Result<CurrentWeather>

    suspend fun getForecastById(id: Int) : Result<Forecast>

    suspend fun saveCurrentWeatherById(id: Int, currentWeather: CurrentWeather) : Result<Unit>

    suspend fun saveForecastById(id: Int, forecast: Forecast) : Result<Unit>

    suspend fun removeForecastsById(id: Int) : Result<Unit>
}