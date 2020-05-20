package com.jeefersan.weatherapp.models

import android.os.Parcelable
import com.jeefersan.weatherapp.misc.getWeatherIconFromWeatherCode
import kotlinx.android.parcel.Parcelize

/**
 * Created by JeeferSan on 29-4-20.
 */
@Parcelize
data class HourlyForecastModel(val hourlyForecast: List<HourlyWeatherModel>) : Parcelable

@Parcelize
data class HourlyWeatherModel(
    val temperature: Int?=0,
    override val timestamp: Long?=0,
    override val icon: String ?= "",
    override val humidity: Int? = 0,
    override val windSpeed: Int? = 0,
    val weatherCode: Int? = 801,
    val rain: Double? = 0.0
) : Parcelable, WeatherModel


fun HourlyWeatherModel.getIconFromWeatherCode(): Int =
    getWeatherIconFromWeatherCode(weatherCode)

