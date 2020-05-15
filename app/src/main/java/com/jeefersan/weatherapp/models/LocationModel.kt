package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 8-5-20.
 */

@Parcelize
data class LocationModel(
    val cityName: String,
    val country: String? = null,
    val lat: Double,
    val long: Double
)
    : Parcelable