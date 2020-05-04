package com.jeefersan.domain

import java.time.LocalTime

/**
 * Created by JeeferSan on 20-4-20.
 */
data class CurrentWeather(
    val id: Long ?= 0,
    val location: Location ?= null,
    val sunset: Long,
    val currentTemp: Int,
    val timestamp: Long,
    val windSpeed: Int,
    val humidity: Int,
    val cloudiness: Int,
    val icon: String,
    val description: String
)