package com.jeefersan.data.weatherforecast.datasources.remote.util

import com.jeefersan.data.weatherforecast.datasources.remote.models.Daily
import com.jeefersan.data.weatherforecast.datasources.remote.models.ForecastResponse
import com.jeefersan.data.weatherforecast.datasources.remote.models.Hourly
import com.jeefersan.domain.*
import kotlin.math.roundToInt

/**
 * Created by JeeferSan on 28-4-20.
 */



fun ForecastResponse.mapToForecast(): Forecast {
    val coordinates = Coordinates(lat, lon)
    val hourlyForecast = hourly.take(12)
        .map {
            it.mapToHourlyWeather()
        }
    val weeklyForecast = daily.map { it.mapToDailyWeather() }

    return Forecast(
        coordinates,
        hourlyForecast = hourlyForecast,
        weeklyForecast = weeklyForecast
    )
}

fun Hourly.mapToHourlyWeather(): HourlyWeather =
    HourlyWeather(
        id = 0,
        temperature = temp.roundToInt(),
        timeStamp = dt.toLong(),
        weatherIcon = weather.first().icon,
        humidity = humidity,
        windSpeed = windSpeed.roundToInt(),
        weatherCode = weather.first().id
    )

fun Daily.mapToDailyWeather(): DailyWeather =
    DailyWeather(
        minTemp = temp.min.roundToInt(),
        maxTemp = temp.max.roundToInt(),
        date = dt.toLong(),
        wind = windSpeed.roundToInt(),
        humidity = humidity,
        icon = weather.first().icon,
        description = weather.first().description
    )

