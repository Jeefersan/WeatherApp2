package com.jeefersan.domain

/**
 * Created by JeeferSan on 3-5-20.
 */


data class FavoriteForecast(
    val favorite: Favorite,
    val weatherForecast: WeatherForecast
)