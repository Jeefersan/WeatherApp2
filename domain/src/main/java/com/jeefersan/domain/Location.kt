package com.jeefersan.domain

/**
 * Created by JeeferSan on 20-4-20.
 */
data class Location(
    val cityId: Long? = null,
    val cityName: String?,
    val coordinates: Coordinates
)


fun Location.mapToCoordinates() = Coordinates(
    coordinates?.lat, coordinates?.long
)