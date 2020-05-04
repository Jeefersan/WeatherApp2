package com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by JeeferSan on 4-5-20.
 */


@Entity(tableName = "hourly_forecast")
data class HourlyWeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    val temperature: Int,
    val timeStamp: Long,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Int,
    val weatherCode: Int
)