package com.jeefersan.domain

/**
 * Created by JeeferSan on 20-4-20.
 */

data class Forecast(
    val hourlyForecast: List<HourlyWeather>,
    val weeklyForecast: List<DailyWeather>
)