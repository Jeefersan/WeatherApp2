package com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather

import androidx.room.*
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity


/**
 * Created by JeeferSan on 4-5-20.
 */

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    val currentTemp: Int,
    val timestamp: Long,
    val sunset: Long,
    val windSpeed: Int,
    val humidity: Int,
    val cloudiness: Int,
    val icon: String,
    val description: String
)