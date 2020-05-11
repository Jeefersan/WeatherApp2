package com.jeefersan.domain

/**
 * Created by JeeferSan on 3-5-20.
 */

data class Favorite(
    var favoriteId: Int=0,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    var lastCurrentUpdate: Long = 0,
    var lastForecastUpdate: Long = 0
)