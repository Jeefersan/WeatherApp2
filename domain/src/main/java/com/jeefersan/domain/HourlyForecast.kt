package com.jeefersan.domain

/**
 * Created by JeeferSan on 28-4-20.
 */

data class HourlyForecast(val hourlyForecast: List<HourlyWeather>)

data class HourlyWeather(
    val id: Int,
    val temperature: Int,
    val timeStamp: Long,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Int,
    val weatherCode: Int
)