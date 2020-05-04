package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "clouds")
    val clouds: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "feels_like")
    val feelsLike: Double,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "rain")
    val rain: Rain?,
    @Json(name = "temp")
    val temp: Double,
    @Json(name = "weather")
    val weather: List<WeatherXX>,
    @Json(name = "wind_deg")
    val windDeg: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double
)