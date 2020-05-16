package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 28-4-20.
 */

@Parcelize
data class CurrentWeatherModel(
    val id: Int?=0,
    val currentTemp: Int?=0,
    val timestamp: Long?=0,
    val sunset: Long?=0,
    val windSpeed: Int?=0,
    val humidity: Int?=0,
    val cloudiness: Int?=0,
    val icon: String?,
    val description: String?=""
) : Parcelable