package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    val country: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "message")
    val message: Double? = 0.0,
    @Json(name = "sunrise")
    val sunrise: Int? = 0,
    @Json(name = "sunset")
    val sunset: Int? = 0,
    @Json(name = "type")
    val type: Int? = 0
)