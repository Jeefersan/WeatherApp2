package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var currentWeatherId: Long,
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
)