package com.jeefersan.weatherapp.framework.location

import com.algolia.search.model.places.PlaceLanguage
import com.algolia.search.model.search.Point
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location

/**
 * Created by JeeferSan on 6-5-20.
 */

fun List<PlaceLanguage>.mapToDomain(): List<Location> =
    this.map { it.mapToDomain() }

fun PlaceLanguage.mapToDomain(): Location =
    Location(
        cityId = null,
        cityName = city.firstOrNull(),
        country = country,
        coordinates = geolocation.mapToCoordinates()
    )

fun List<Point>.mapToCoordinates() =
    Coordinates(this.first().latitude.toDouble(), this.first().longitude.toDouble())