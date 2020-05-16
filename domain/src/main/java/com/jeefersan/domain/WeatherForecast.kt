package com.jeefersan.domain

/**
 * Created by JeeferSan on 28-4-20.
 */

data class WeatherForecast(
    val id: Long?,
    val currentWeather: CurrentWeather,
    val hourlyForecast: HourlyForecast,
    val weeklyForecast: WeeklyForecast
)

