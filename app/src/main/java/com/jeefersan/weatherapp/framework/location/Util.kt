package com.jeefersan.weatherapp.framework.location

import com.algolia.search.model.places.PlaceLanguage
import com.algolia.search.model.search.Point
import com.google.android.gms.location.LocationRequest
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import java.util.concurrent.TimeUnit

/**
 * Created by JeeferSan on 6-5-20.
 */

fun List<PlaceLanguage>.mapToDomain(): List<Location> =
    this.map { it.mapToDomain() }

fun PlaceLanguage.mapToDomain(): Location =
    Location(
        cityId = null,
        cityName = localNamesOrNull?.first() ?: city.first(),
        country = countryOrNull,
        coordinates = geolocation.mapToCoordinates()
    )

fun List<Point>.mapToCoordinates() =
    Coordinates(this.first().latitude.toDouble(), this.first().longitude.toDouble())

fun getLocationRequest(): LocationRequest = LocationRequest().apply {
    interval = TimeUnit.SECONDS.toSeconds(60)
    fastestInterval = TimeUnit.SECONDS.toSeconds(30)
    maxWaitTime = TimeUnit.SECONDS.toSeconds(90)
    priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
}
