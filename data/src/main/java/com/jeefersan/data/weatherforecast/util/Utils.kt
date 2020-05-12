package com.jeefersan.data.weatherforecast.util

import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity
import com.jeefersan.domain.*

/**
 * Created by JeeferSan on 3-5-20.
 */


fun CurrentWeather.mapToLocalWithId(id: Int): CurrentWeatherEntity = CurrentWeatherEntity(
    favoriteId = id,
    currentTemp = currentTemp,
    sunset = sunset,
    timestamp = timestamp,
    windSpeed = windSpeed,
    humidity = humidity,
    cloudiness = cloudiness,
    icon = icon,
    description = description ?: ""
)

fun CurrentWeatherEntity.mapToDomain(): CurrentWeather = CurrentWeather(
    id = favoriteId,
    description = description,
    icon = icon,
    humidity = humidity,
    windSpeed = windSpeed,
    sunset = sunset,
    currentTemp = currentTemp,
    timestamp = timestamp,
    cloudiness = cloudiness
)


fun HourlyWeather.mapToLocalWithId(id: Int): HourlyWeatherEntity =
    HourlyWeatherEntity(
        favoriteId = id,
        temperature = temperature,
        timeStamp = timeStamp,
        weatherIcon = weatherIcon,
        humidity = humidity,
        windSpeed = windSpeed,
        weatherCode = weatherCode,
        rain = rain
    )


fun DailyWeather.mapToLocalWithId(id: Int): DailyWeatherEntity =
    DailyWeatherEntity(
        favoriteId = id,
        minTemp = minTemp,
        maxTemp = maxTemp,
        date = date,
        wind = wind,
        humidity = humidity,
        icon = icon,
        description = description
    )

fun FavoriteEntity.mapToDomain(): Favorite =
    Favorite(
        favoriteId = id,
        longitude = longitude,
        latitude = latitude,
        lastCurrentUpdate = lastCurrentUpdate,
        lastForecastUpdate = lastForecastUpdate,
        cityName = cityName
    )

