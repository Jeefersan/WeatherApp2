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
    override val timestamp: Long?=0,
    override val windSpeed: Int?=0,
    override val humidity: Int?=0,
    override val icon: String?,
    val description: String?=""
) : Parcelable, WeatherModel