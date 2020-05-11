package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 5-5-20.
 */

//@Parcelize
//data class FavoriteForecastModel(
//    val favoriteModel: FavoriteModel,
//    var weatherForecastModel: WeatherForecastModel
//) :
//    Parcelable


data class FavoriteCurrentWeatherModel(
    val id: Int,
    val cityName: String,
    val wind: Int,
    val humidity: Int,
    val cloudiness: Int,
    val currentTemp: Int,
    val icon: String,
    var isExpanded : Boolean
)