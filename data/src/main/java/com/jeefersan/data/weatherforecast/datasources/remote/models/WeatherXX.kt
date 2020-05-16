package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherXX(
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "icon")
    val icon: String? = "01d",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "main")
    val main: String? = ""
)