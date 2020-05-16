package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    val lat: Double? = 0.0,
    @Json(name = "lon")
    val lon: Double? = 0.0
)