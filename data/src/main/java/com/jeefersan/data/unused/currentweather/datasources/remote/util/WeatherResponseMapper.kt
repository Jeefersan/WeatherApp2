package com.jeefersan.data.unused.currentweather.datasources.remote.util

import com.jeefersan.data.weatherforecast.datasources.remote.models.WeatherResponse
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.domain.CurrentWeather
import kotlin.math.roundToInt

/**
 * Created by JeeferSan on 22-4-20.
 */


fun WeatherResponse.mapToWeather(): CurrentWeather {
    val location = Location(
        cityId = id,
        cityName = name,
        coordinates = Coordinates(coord.lat, coord.lon)
    )

    return CurrentWeather(
        currentTemp = main.temp.roundToInt(),
        timestamp = dt,
        windSpeed = wind.speed.roundToInt(),
        humidity = main.humidity,
        cloudiness = clouds.all,
        icon = weather.first().icon,
        description = weather.first().description,
        sunset = -1,
        id = id
    )
}