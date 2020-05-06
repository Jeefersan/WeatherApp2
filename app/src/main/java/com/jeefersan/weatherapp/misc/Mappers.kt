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
        description = description
    )

fun HourlyForecast.mapToHourlyForecastModel(): HourlyForecastModel =
    HourlyForecastModel(hourlyWeatherEntity.map { it.mapToHourlyWeatherModel() })

fun WeeklyForecast.mapToWeeklyForecastModel(): WeeklyForecastModel =
    WeeklyForecastModel(weeklyForecast.map { it.mapToDailyWeatherModel() })

fun HourlyWeather.mapToHourlyWeatherModel(): HourlyWeatherModel =
    HourlyWeatherModel(temperature, timeStamp, weatherIcon, humidity, windSpeed, weatherCode)

fun DailyWeather.mapToDailyWeatherModel(): DailyWeatherModel =
    DailyWeatherModel(minTemp, maxTemp, date, wind, humidity, icon, description)

fun Favorite.mapToPresentation() : FavoriteModel =
    FavoriteModel(favoriteId, cityName, latitude, longitude, lastUpdateTime)

fun FavoriteForecast.mapToPresentation() : FavoriteForecastModel =
    FavoriteForecastModel(favorite.mapToPresentation(), weatherForecast.mapToPresentation())

fun FavoriteModel.mapToDomain() : Favorite =
    Favorite(favoriteId, cityName, latitude, longitude, lastUpdateTime)

fun List<FavoriteModel>.mapToDomain() : List<Favorite> =
    this.map { it.mapToDomain() }

fun List<FavoriteForecast>.mapToPresentation() : List<FavoriteForecastModel> =
    this.map { it.mapToPresentation() }