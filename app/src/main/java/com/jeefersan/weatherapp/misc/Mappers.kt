package com.jeefersan.weatherapp.misc

import com.jeefersan.domain.*
import com.jeefersan.weatherapp.models.*

/**
 * Created by JeeferSan on 28-4-20.
 */


fun WeatherForecast.mapToPresentation(): WeatherForecastModel =
    WeatherForecastModel(
        currentWeather.mapToWeatherModel(),
        hourlyForecast.mapToHourlyForecastModel(),
        weeklyForecast.mapToWeeklyForecastModel()
    )

fun CurrentWeather.mapToWeatherModel(): CurrentWeatherModel =
    CurrentWeatherModel(
        id = id,
        sunset = sunset,
        currentTemp = currentTemp,
        timestamp = timestamp,
        windSpeed = windSpeed,
        humidity = humidity,
        cloudiness = cloudiness,
        icon = icon,
        description = description ?: ""
    )

fun HourlyForecast.mapToHourlyForecastModel(): HourlyForecastModel =
    HourlyForecastModel(hourlyForecast.map { it.mapToHourlyWeatherModel() })

fun WeeklyForecast.mapToWeeklyForecastModel(): WeeklyForecastModel =
    WeeklyForecastModel(weeklyForecast.map { it.mapToDailyWeatherModel() })

fun HourlyWeather.mapToHourlyWeatherModel(): HourlyWeatherModel =
    HourlyWeatherModel(temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode)

fun DailyWeather.mapToDailyWeatherModel(): DailyWeatherModel =
    DailyWeatherModel(minTemp, maxTemp, date, wind, humidity, icon, description)

fun Favorite.mapToPresentation(): FavoriteModel =
    FavoriteModel(favoriteId, cityName)

fun FavoriteCurrentWeather.mapToPresentation() =
    FavoriteCurrentWeatherModel(
        id = favorite.favoriteId,
        cityName = favorite.cityName,
        wind = currentWeather.windSpeed,
        cloudiness = currentWeather.cloudiness,
        currentTemp = currentWeather.currentTemp,
        humidity = currentWeather.humidity,
        icon = currentWeather.icon,
        isExpanded = false
    )

