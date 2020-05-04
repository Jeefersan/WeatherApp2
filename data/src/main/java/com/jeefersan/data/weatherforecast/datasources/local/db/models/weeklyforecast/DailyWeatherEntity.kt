package com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by JeeferSan on 4-5-20.
 */

@Entity(tableName = "weekly_forecast")
data class DailyWeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    val favoriteId: Long,
    val minTemp: Int,
    val maxTemp: Int,
    val date: Long,
    val wind: Int,
    val humidity: Int,
    val icon: String,
    val description: String
)