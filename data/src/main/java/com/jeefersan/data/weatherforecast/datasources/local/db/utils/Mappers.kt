package com.jeefersan.data.weatherforecast.datasources.local.db.utils

import com.jeefersan.data.weatherforecast.datasources.local.db.models.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyForecastWithFavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.WeeklyForecastWithFavoriteEntity
import com.jeefersan.domain.*

/**
 * Created by JeeferSan on 26-4-20.
 */

fun CurrentWeatherEntity.mapToDomain(): CurrentWeather =

    CurrentWeather(
        currentTemp = currentTemp,
        timestamp = timestamp,
        windSpeed = windSpeed,
        humidity = humidity,
        cloudiness = cloudiness,
        icon = icon,
        description = description,
        sunset = sunset,
        id = id
    )


fun CurrentWeather.mapToLocal(): CurrentWeatherEntity =
    CurrentWeatherEntity(
        id = id,
        currentTemp = currentTemp,
        timestamp = timestamp,
        sunset = sunset,
        windSpeed = windSpeed,
        humidity = humidity,
        cloudiness = cloudiness,
        icon = icon,
        description = description

//        0,
//        0,
//        currentTemp,
//        timestamp,
//        sunset,
//        windSpeed,
//        humidity,
//        cloudiness,
//        null,
//        null,
//        icon,
//        description
    )

fun HourlyForecastWithFavoriteEntity.mapToDomain(): HourlyForecast {
    return HourlyForecast(hourlyForecast.map { it.mapToHourlyWeather() })
}

//fun HourlyForecast.mapToLocal(): HourlyForecastWithFavoriteEntity =
//    HourlyForecastWithFavoriteEntity(
//        hourlyWeatherEntity.map { it.mapToHourlyWeatherEntity() })

fun HourlyWeatherEntity.mapToHourlyWeather(): HourlyWeather =
    HourlyWeather(
//        hourlyWeatherId,
        0, temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode
    )

fun WeeklyForecastWithFavoriteEntity.mapToWeeklyForecast(): WeeklyForecast =
    WeeklyForecast(0, weeklyForecast.map { it.mapToDailyWeather() })

fun DailyWeatherEntity.mapToDailyWeather(): DailyWeather =
    DailyWeather(minTemp, maxTemp, date, wind, humidity, icon, description)

fun DailyWeather.mapToDailyWeatherEntity(): DailyWeatherEntity =
    DailyWeatherEntity(
        0,
        0,
        minTemp,
        maxTemp,
        date,
        wind,
        humidity,
        icon,
        description
    )

fun WeatherForecastEntity.mapToWeatherForecast(): WeatherForecast =
    WeatherForecast(
        id,
        currentWeatherEntity.mapToDomain(),
//        hourlyForecastEntity.mapToHourlyForecast(),
//        weeklyForecastEntity.mapToWeeklyForecast()
        HourlyForecast(hourlyForecastEntity.map { it.mapToHourlyWeather() }),
        WeeklyForecast(id, weeklyForecastEntity.map { it.mapToDailyWeather() })
    )

fun WeatherForecast.mapToWeatherForecastEntity(): WeatherForecastEntity =
    WeatherForecastEntity(
        id!!,
//        currentWeather.mapToWeatherEntity(),
//        hourlyForecast.mapToHourlyForecastEntity(),
//        weeklyForecast.mapToWeeklyForecastEntity()
        currentWeather.mapToLocal(),
        hourlyForecast.hourlyWeatherEntity.map { it.mapToLocal() },
        weeklyForecast.weeklyForecast.map { it.mapToDailyWeatherEntity() }
    )

fun HourlyWeather.mapToLocal(): HourlyWeatherEntity =
    HourlyWeatherEntity(
        id,
        temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode
    )

//fun WeeklyForecast.mapToWeeklyForecastEntity(): WeeklyForecastWithFavoriteEntity =
//    WeeklyForecastWithFavoriteEntity(
//        weeklyForecast.map { it.mapToDailyWeatherEntity() })