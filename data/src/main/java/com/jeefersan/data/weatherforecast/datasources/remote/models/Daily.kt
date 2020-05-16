package com.jeefersan.data.weatherforecast.datasources.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "clouds")
    val clouds: Int? = 0,
    @Json(name = "dew_point")
    val dewPoint: Double? = 0.0,
    @Json(name = "dt")
    val dt: Int? = 0,
    @Json(name = "feels_like")
    val feelsLike: FeelsLike? = FeelsLike(),
    @Json(name = "humidity")
    val humidity: Int? = 0,
    @Json(name = "pressure")
    val pressure: Int? = 0,
    @Json(name = "sunrise")
    val sunrise: Int? = 0,
    @Json(name = "sunset")
    val sunset: Int? = 0,
    @Json(name = "temp")
    val temp: Temp? = Temp(),
    @Json(name = "uvi")
    val uvi: Double? = 0.0,
    @Json(name = "weather")
    val weather: List<WeatherXX>? = listOf(),
    @Json(name = "wind_deg")
    val windDeg: Int? = 0,
    @Json(name = "wind_speed")
    val windSpeed: Double? = 0.0
)