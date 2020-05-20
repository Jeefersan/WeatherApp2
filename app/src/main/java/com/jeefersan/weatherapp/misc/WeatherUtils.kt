package com.jeefersan.weatherapp.misc

import com.jeefersan.weatherapp.R

/**
 * Created by JeeferSan on 29-4-20.
 */


fun getWeatherIconFromWeatherCode(id: Int?) = when (id) {
    in 200..232 -> R.drawable.ic_weather_cloud_bolt_rain
    in 300..321 -> R.drawable.ic_weather_cloud_rain
    in 500..504 -> R.drawable.ic_weather_cloud_sun_rain
    in 511..531 -> R.drawable.ic_weather_clouds_rain
    in 600..613 -> R.drawable.ic_weather_cloud_snow
    in 614..622 -> R.drawable.ic_weather_cloud_more_snow
    in 701..762 -> R.drawable.ic_weather_wind
    771, 781 -> R.drawable.ic_weather_storm
    800 -> R.drawable.ic_sun
    801 -> R.drawable.ic_weather_cloud_sun
    in 802..804 -> R.drawable.ic_weather_clouds

    else -> R.drawable.ic_weather_cloud_sun
}

fun getWeatherIconRes(icon: String?) = when (icon) {
   "11d", "11n" -> R.drawable.ic_weather_cloud_bolt_rain
    "09d", "09n" -> R.drawable.ic_weather_cloud_rain
    "10d" -> R.drawable.ic_weather_cloud_sun_rain
    "10n" -> R.drawable.ic_weather_clouds_rain
    "13d" -> R.drawable.ic_weather_cloud_snow
    "13n" -> R.drawable.ic_weather_cloud_more_snow
    "50d", "50n" -> R.drawable.ic_weather_cloud_sun
    "01d" -> R.drawable.ic_sun
    "02d" -> R.drawable.ic_weather_cloud_sun
    "03d", "03n" -> R.drawable.ic_weather_clouds
    "02n" -> R.drawable.ic_weather_cloud_moon
    else -> R.drawable.ic_weather_clouds
}

fun getOpenWeatherIconRes(icon: String?) = when (icon) {
    "01d" -> R.drawable.ic_01d
    "01n" -> R.drawable.ic_01n
    "02d" -> R.drawable.ic_02d
    "02n" -> R.drawable.ic_02n
    "03d" -> R.drawable.ic_03d
    "03n" -> R.drawable.ic_03n
    "04n" -> R.drawable.ic_04n
    "04d" -> R.drawable.ic_04d
    "09d" -> R.drawable.ic_09d
    "09n" -> R.drawable.ic_09n
    "10d" -> R.drawable.ic_10d
    "10n" -> R.drawable.ic_10n
    "11d" -> R.drawable.ic_11d
    "13d" -> R.drawable.ic_13d
    "13n" -> R.drawable.ic_13n
    "50d" -> R.drawable.ic_50d
    "50n" -> R.drawable.ic_50n

    else -> -1
}




