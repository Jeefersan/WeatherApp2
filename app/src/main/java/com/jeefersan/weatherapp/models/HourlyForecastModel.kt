package com.jeefersan.weatherapp.models

import android.os.Parcelable
import com.jeefersan.weatherapp.misc.getWeatherIconRes
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
    val weatherCode: Int,
    val rain: Double?= 0.0
) : Parcelable


fun HourlyWeatherModel.getIconRes(): Int =
    getWeatherIconRes(weatherCode)

