package com.jeefersan.data.weatherforecast.util

import com.jeefersan.data.weatherforecast.datasources.remote.models.Daily
import com.jeefersan.data.weatherforecast.datasources.remote.models.ForecastResponse
import com.jeefersan.data.weatherforecast.datasources.remote.models.Hourly
import com.jeefersan.data.weatherforecast.datasources.remote.util.mapToDailyWeather
import com.jeefersan.data.weatherforecast.datasources.remote.util.mapToHourlyWeather
import com.jeefersan.domain.*
import kotlin.math.roundToInt

/**
 * Created by JeeferSan on 29-4-20.
 */


fun ForecastResponse.mapToWeatherForecast(): WeatherForecast {

    val currentWeather = CurrentWeather(
        currentTemp = current.temp.roundToInt(),
        timestamp = current.dt.toLong(),
        windSpeed = current.windSpeed.roundToInt(),
        humidity = current.humidity,
        cloudiness = current.clouds,
        sunset = current.sunset.toLong(),
        icon = current.weather.first().icon,
        description = current.weather.first().description,
        id = 0
    )

    val hourlyForecast = hourly.take(12)
        .map {
            it.mapToHourlyWeather()
        }
    val weeklyForecast = daily.take(7)
        .map { it.mapToDailyWeather() }



    return WeatherForecast(
        currentWeather = currentWeather,
        hourlyForecast = HourlyForecast(hourlyForecast),
        weeklyForecast = WeeklyForecast(null, weeklyForecast),
        id = -1
    )
}

fun ForecastResponse.mapToForecast(): Forecast =
    Forecast(hourly.map { it.mapToHourlyWeather() }, daily.take(7).map { it.mapToDailyWeather() })

fun Hourly.mapToHourlyWeather(): HourlyWeather =
    HourlyWeather(
        temperature = temp.roundToInt(),
        timeStamp = dt.toLong(),
        weatherIcon = weather.first().icon,
        humidity = humidity,
        windSpeed = windSpeed.roundToInt(),
        weatherCode = weather.first().id,
        id = 0
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

