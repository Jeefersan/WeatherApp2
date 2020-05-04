package com.jeefersan.weatherapp.presentation.home.utils

import com.jeefersan.domain.*
import com.jeefersan.weatherapp.models.*

/**
 * Created by JeeferSan on 28-4-20.
 */


fun WeatherForecast.mapToWeatherForecastModel(): WeatherForecastModel =
    WeatherForecastModel(currentWeather.mapToWeatherModel(),
    hourlyForecast.mapToHourlyForecastModel(),
    weeklyForecast.mapToWeeklyForecastModel())

fun CurrentWeather.mapToWeatherModel(): CurrentWeatherModel =
    CurrentWeatherModel(
        cityName = location?.cityName,
        cityId = location?.cityId,
        sunset = sunset,
        currentTemp = currentTemp,
        timestamp = timestamp,
        windSpeed = windSpeed,
        humidity = humidity,
        cloudiness = cloudiness,
        icon = icon,
        description = description
    )

fun HourlyForecast.mapToHourlyForecastModel() : HourlyForecastModel =
    HourlyForecastModel(hourlyWeatherEntity.map { it.mapToHourlyWeatherModel() })

fun WeeklyForecast.mapToWeeklyForecastModel(): WeeklyForecastModel =
    WeeklyForecastModel(weeklyForecast.map { it.mapToDailyWeatherModel() })

fun HourlyWeather.mapToHourlyWeatherModel(): HourlyWeatherModel =
    HourlyWeatherModel(temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode)

fun DailyWeather.mapToDailyWeatherModel(): DailyWeatherModel =
    DailyWeatherModel(minTemp, maxTemp, date, wind, humidity, icon, description)