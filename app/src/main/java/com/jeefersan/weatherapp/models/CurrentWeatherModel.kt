package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 28-4-20.
 */

@Parcelize
data class CurrentWeatherModel(
    val currentTemp: Int,
    val timestamp: Long,
    val sunset: Long,
    val windSpeed: Int,
    val humidity: Int,
    val cloudiness: Int,
    val cityId: Long?,
    val cityName: String?,
    val icon: String,
    val description: String
) : Parcelable