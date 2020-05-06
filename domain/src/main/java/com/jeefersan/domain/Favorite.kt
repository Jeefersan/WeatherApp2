package com.jeefersan.domain

/**
 * Created by JeeferSan on 3-5-20.
 */

data class Favorite(
    val favoriteId: Long = 0,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val lastUpdateTime: Long
)

