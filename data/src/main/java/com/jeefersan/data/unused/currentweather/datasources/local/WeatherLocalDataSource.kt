package com.jeefersan.data.unused.currentweather.datasources.local

import com.jeefersan.domain.CurrentWeather

/**
 * Created by JeeferSan on 23-4-20.
 */
interface WeatherLocalDataSource {
    suspend fun getWeatherByCityId(cityId: Long): CurrentWeather

}