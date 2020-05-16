package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 29-4-20.
 */

@Parcelize
data class WeeklyForecastModel(val weeklyForecast: List<DailyWeatherModel>) : Parcelable

@Parcelize
data class DailyWeatherModel(
    val minTemp: Int?=0,
    val maxTemp: Int?=0,
    val date: Long?=0,
    val wind: Int?=0,
    val humidity: Int?=0,
    val icon: String?,
    val description: String?=""
) : Parcelable