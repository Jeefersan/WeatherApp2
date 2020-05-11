package com.jeefersan.domain


/**
 * Created by JeeferSan on 20-4-20.
 */
data class Location(
    val cityId: Int? = null,
    val cityName: String,
    val country: String? = null,
    val coordinates: Coordinates
)

