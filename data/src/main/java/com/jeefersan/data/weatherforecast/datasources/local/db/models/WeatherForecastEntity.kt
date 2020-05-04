package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.*

/**
 * Created by JeeferSan on 3-5-20.
 */
@Entity(tableName = "weather_forecast")
data class WeatherForecastEntity(
    @PrimaryKey
    var id: Long
//val currentWeatherEntity: CurrentWeatherEntity,
//val hourlyForecastEntity: List<HourlyWeatherEntity>,
//    val weeklyForecastEntity: List<DailyWeatherEntity>
)


//@Entity(tableName = "weather_forecast")
//data class WeatherForecastEntity(
//    @PrimaryKey
//    val id: Long,
//    @Embedded val currentWeatherEntity: CurrentWeatherEntity,
//    @Embedded val hourlyForecastEntity: HourlyForecastEntity,
//    @Embedded val weeklyForecastEntity: WeeklyForecastEntity
//
////    @Embedded val hourlyForecastEntity: List<HourlyWeatherEntity>,
////    @Embedded val weeklyForecastEntity: List<DailyWeatherEntity>
//)
