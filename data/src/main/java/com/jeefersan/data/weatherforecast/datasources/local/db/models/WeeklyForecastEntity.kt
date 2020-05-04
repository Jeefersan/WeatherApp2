package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by JeeferSan on 3-5-20.
 */


//@Entity(tableName = "weekly_forecast")
data class WeeklyForecastEntity(
//    @PrimaryKey(autoGenerate = true)
//    var id: Long = 0,
//    @Embedded
    val weeklyForecast: List<DailyWeatherEntity>
)

@Entity
data class DailyWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val dailyWeatherId: Long,
    val minTemp: Int,
    val maxTemp: Int,
    val date: Long,
    val wind: Int,
    val humidity: Int,
    val icon: String,
    val description: String
)