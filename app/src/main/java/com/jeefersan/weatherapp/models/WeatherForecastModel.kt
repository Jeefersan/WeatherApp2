package com.jeefersan.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 29-4-20.
 */

@Parcelize
data class WeatherForecastModel(
    val currentWeatherModel: CurrentWeatherModel,
    val hourlyForecast: HourlyForecastModel,
    val weeklyForecast: WeeklyForecastModel
) : Parcelable
