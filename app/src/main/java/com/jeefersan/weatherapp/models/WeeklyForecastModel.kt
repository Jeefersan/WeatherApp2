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
    val minTemp: Int,
    val maxTemp: Int,
    val date: Long,
    val wind: Int,
    val humidity: Int,
    val icon: String,
    val description: String
) : Parcelable