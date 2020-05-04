package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.jeefersan.data.unused.currentweather.datasources.remote.models.Weather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "base")
    val base: String,
    @Json(name = "clouds")
    val clouds: Clouds,
    @Json(name = "cod")
    val cod: Int,
    @Json(name = "coord")
    val coord: Coord,
    @Json(name = "dt")
    val dt: Long,
    @Json(name = "id")
    val id: Long,
    @Json(name = "main")
    val main: Main,
    @Json(name = "name")
    val name: String,
    @Json(name = "sys")
    val sys: Sys,
    @Json(name = "timezone")
    val timezone: Int,
    @Json(name = "visibility")
    val visibility: Int,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "wind")
    val wind: Wind
)