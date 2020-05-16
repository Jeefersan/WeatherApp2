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
    val temperature: Int?=0,
    val timeStamp: Long?=0,
    val weatherIcon: String ?= "",
    val humidity: Int? = 0,
    val windSpeed: Int? = 0,
    val weatherCode: Int? = 801,
    val rain: Double? = 0.0
) : Parcelable


fun HourlyWeatherModel.getIconRes(): Int =
    getWeatherIconRes(weatherCode?: 801)

