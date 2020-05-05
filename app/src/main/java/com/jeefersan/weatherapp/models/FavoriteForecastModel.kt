package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 5-5-20.
 */

@Parcelize
data class FavoriteForecastModel(val favoriteModel: FavoriteModel, val weatherForecastModel: WeatherForecastModel) :
    Parcelable