package com.jeefersan.data.weatherforecast.datasources.local.db.utils

import com.jeefersan.data.weatherforecast.datasources.local.db.models.*
import com.jeefersan.domain.*

/**
 * Created by JeeferSan on 26-4-20.
 */

fun CurrentWeatherEntity.mapToWeather(): CurrentWeather {
    val location = cityName?.let {
        Location(
            cityId = cityId,
            cityName = cityName,
            coordinates = Coordinates(-1.0, -1.0)
        )
    }
    return CurrentWeather(
        location = location,
        currentTemp = currentTemp,
        timestamp = timestamp,
        windSpeed = windSpeed,
        humidity = humidity,
        cloudiness = cloudiness,
        icon = icon,
        description = description,
        sunset = -1,
        id = null
    )
}

fun CurrentWeather.mapToWeatherEntity(): CurrentWeatherEntity =
    CurrentWeatherEntity(
        0,
        currentTemp,
        timestamp,
        sunset,
        windSpeed,
        humidity,
        cloudiness,
        null,
        null,
        icon,
        description
    )

fun HourlyForecastEntity.mapToHourlyForecast(): HourlyForecast {
    return HourlyForecast(hourlyForecast.map { it.mapToHourlyWeather() })
}

fun HourlyForecast.mapToHourlyForecastEntity(): HourlyForecastEntity =
    HourlyForecastEntity(hourlyWeatherEntity.map { it.mapToHourlyWeatherEntity() })

fun HourlyWeatherEntity.mapToHourlyWeather(): HourlyWeather =
    HourlyWeather(
//        hourlyWeatherId,
        0, temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode
    )

fun WeeklyForecastEntity.mapToWeeklyForecast(): WeeklyForecast =
    WeeklyForecast(0, weeklyForecast.map { it.mapToDailyWeather() })

fun DailyWeatherEntity.mapToDailyWeather(): DailyWeather =
    DailyWeather(minTemp, maxTemp, date, wind, humidity, icon, description)

fun DailyWeather.mapToDailyWeatherEntity(): DailyWeatherEntity =
    DailyWeatherEntity(0, minTemp, maxTemp, date, wind, humidity, icon, description)
//
//fun WeatherForecastEntity.mapToWeatherForecast(): WeatherForecast =
//    WeatherForecast(
//        id,
//        currentWeatherEntity.mapToWeather(),
////        hourlyForecastEntity.mapToHourlyForecast(),
////        weeklyForecastEntity.mapToWeeklyForecast()
//        HourlyForecast(hourlyForecastEntity.map { it.mapToHourlyWeather() }),
//        WeeklyForecast(id, weeklyForecastEntity.map { it.mapToDailyWeather() })
//    )
//
//fun WeatherForecast.mapToWeatherForecastEntity(): WeatherForecastEntity =
//    WeatherForecastEntity(
//        id!!,
////        currentWeather.mapToWeatherEntity(),
////        hourlyForecast.mapToHourlyForecastEntity(),
////        weeklyForecast.mapToWeeklyForecastEntity()
//        currentWeather.mapToWeatherEntity(),
//        hourlyForecast.hourlyWeatherEntity.map { it.mapToHourlyWeatherEntity() },
//        weeklyForecast.weeklyForecast.map { it.mapToDailyWeatherEntity() }
//    )

fun HourlyWeather.mapToHourlyWeatherEntity(): HourlyWeatherEntity =
    HourlyWeatherEntity(0, temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode)

fun WeeklyForecast.mapToWeeklyForecastEntity(): WeeklyForecastEntity =
    WeeklyForecastEntity(weeklyForecast.map { it.mapToDailyWeatherEntity() })