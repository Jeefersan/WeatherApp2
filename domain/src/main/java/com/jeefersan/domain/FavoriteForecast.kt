package com.jeefersan.domain

/**
 * Created by JeeferSan on 3-5-20.
 */


data class FavoriteForecast(
    val favorite: Favorite,
    val hourlyForecast: HourlyForecast)

data class FavoriteCurrentWeather(
    val favorite: Favorite,
    val currentWeather: CurrentWeather
)

data class FavoriteWeatherForecast(
    val favorite: Favorite,
    val currentWeather: CurrentWeather,
    val hourlyForecast: HourlyForecast,
    val weeklyForecast: WeeklyForecast
)