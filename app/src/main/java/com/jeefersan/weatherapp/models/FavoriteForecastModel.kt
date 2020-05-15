package com.jeefersan.weatherapp.models

/**
 * Created by JeeferSan on 5-5-20.
 */


data class FavoriteCurrentWeatherModel(
    val id: Int,
    val cityName: String,
    val wind: Int,
    val humidity: Int,
    val cloudiness: Int,
    val currentTemp: Int,
    val icon: String,
    var isExpanded : Boolean
)