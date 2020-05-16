package com.jeefersan.domain

/**
 * Created by JeeferSan on 20-4-20.
 */

data class WeeklyForecast(val id: Long?= 0, val weeklyForecast: List<DailyWeather>)

data class DailyWeather(
    val minTemp: Int?,
    val maxTemp: Int?,
    val date: Long?,
    val wind: Int?,
    val humidity: Int?,
    val icon: String?,
    val description: String?
)