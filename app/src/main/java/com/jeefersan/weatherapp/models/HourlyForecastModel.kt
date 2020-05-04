package com.jeefersan.weatherapp.models

import android.os.Parcelable
import com.jeefersan.weatherapp.misc.WeatherUtils
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 29-4-20.
 */
@Parcelize
data class HourlyForecastModel(val hourlyForecast: List<HourlyWeatherModel>) : Parcelable

@Parcelize
data class HourlyWeatherModel(
    val temperature: Int,
    val timeStamp: Long,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Int,
    val weatherCode: Int
) : Parcelable


fun HourlyWeatherModel.getIconRes(): Int =
    WeatherUtils.getWeatherIconRes(weatherCode)

