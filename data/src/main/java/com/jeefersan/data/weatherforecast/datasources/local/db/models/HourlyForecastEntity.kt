package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


data class HourlyForecastEntity(
//    @Embedded val hourlyWeatherEntity: List<HourlyWeatherEntity>,
    val hourlyForecast: List<HourlyWeatherEntity>
)


data class HourlyWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var hourlyWeatherId: Long,
    val temperature: Int,
    val timeStamp: Long,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Int,
    val weatherCode: Int
)