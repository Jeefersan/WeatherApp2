package com.jeefersan.weatherapp.models

/**
 * Created by JeeferSan on 5-5-20.
 */


data class FavoriteCurrentWeatherModel(
    val id: Int,
    val cityName: String,
    val wind: Int?=0,
    val humidity: Int?=0,
    val cloudiness: Int?=0,
    val currentTemp: Int?=0,
    val icon: String?="",
    var isExpanded : Boolean
)