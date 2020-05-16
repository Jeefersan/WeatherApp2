package com.jeefersan.data.weatherforecast.datasources.remote.util

import com.jeefersan.data.weatherforecast.datasources.remote.models.Daily
import com.jeefersan.data.weatherforecast.datasources.remote.models.Hourly
import com.jeefersan.data.weatherforecast.datasources.remote.models.WeatherResponse
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.DailyWeather
import com.jeefersan.domain.HourlyWeather
import kotlin.math.roundToInt

/**
 * Created by JeeferSan on 28-4-20.
 */




fun Hourly.mapToHourlyWeather(): HourlyWeather =
    HourlyWeather(
        id = 0,
        temperature = temp!!.roundToInt(),
        timeStamp = dt!!.toLong(),
        weatherIcon = weather.first().icon!!,
        humidity = humidity,
        windSpeed = windSpeed!!.roundToInt(),
        weatherCode = weather.first().id!!,
        rain = rain?.h
    )

fun Daily.mapToDailyWeather(): DailyWeather =
    DailyWeather(
        minTemp = temp!!.min!!.roundToInt(),
        maxTemp = temp.max!!.roundToInt(),
        date = dt!!.toLong(),
        wind = windSpeed!!.roundToInt(),
        humidity = humidity!!,
        icon = weather!!.first().icon!!,
        description = weather.first().description!!
    )

fun WeatherResponse.mapToWeather(): CurrentWeather =

    CurrentWeather(
        currentTemp = main!!.temp!!.roundToInt(),
        timestamp = dt!!.toLong(),
        windSpeed = wind!!.speed!!.roundToInt(),
        humidity = main.humidity,
        cloudiness = clouds!!.all!!,
        icon = weather!!.first().icon!!,
        description = weather?.first()?.description,
        sunset = sys?.sunset!!.toLong(),
        id = 0
    )
