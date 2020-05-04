package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "clouds")
    val clouds: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "feels_like")
    val feelsLike: FeelsLike,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "rain")
    val rain: Double?,
    @Json(name = "sunrise")
    val sunrise: Int,
    @Json(name = "sunset")
    val sunset: Int,
    @Json(name = "temp")
    val temp: Temp,
    @Json(name = "uvi")
    val uvi: Double,
    @Json(name = "weather")
    val weather: List<WeatherX>,
    @Json(name = "wind_deg")
    val windDeg: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double
)