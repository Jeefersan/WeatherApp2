package com.jeefersan.weatherapp.models

import com.jeefersan.weatherapp.misc.getOpenWeatherIconRes
import com.jeefersan.weatherapp.misc.getWeatherIconRes
import com.jeefersan.weatherapp.misc.toAbbreviatedWeekDay
import com.jeefersan.weatherapp.misc.toHourlyDate

/**
 * Created by JeeferSan on 18-5-20.
 */
interface WeatherModel {
    val timestamp: Long?
    val windSpeed: Int?
    val humidity: Int?
    val icon: String?

    private fun getIconRes(): Int =
        getWeatherIconRes(icon)

    private fun getOpenIconRes(): Int =
        getOpenWeatherIconRes(icon)

    private fun getHourlyDate(): String? =
        timestamp?.toHourlyDate()

    private fun getAbbreviatedWeekDay(): String? =
        timestamp?.toAbbreviatedWeekDay()

}