package com.jeefersan.domain


/**
 * Created by JeeferSan on 20-4-20.
 */
data class Location(
    val cityId: Long? = null,
    val cityName: String? = null,
    val country: String? = null,
    val coordinates: Coordinates
)

