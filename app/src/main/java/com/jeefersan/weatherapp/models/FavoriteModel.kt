package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 5-5-20.
 */

@Parcelize
data class FavoriteModel(
    val favoriteId: Long,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val lastUpdateTime: Long
) : Parcelable