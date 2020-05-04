package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity

/**
 * Created by JeeferSan on 3-5-20.
 */
//@Entity(tableName = "weather_forecast")
data class WeatherForecastEntity(
    @PrimaryKey
    var id: Long,
    val currentWeatherEntity: CurrentWeatherEntity,
    val hourlyForecastEntity: List<HourlyWeatherEntity>,
    val weeklyForecastEntity: List<DailyWeatherEntity>
)


//@Entity(tableName = "weather_forecast")
//data class WeatherForecastEntity(
//    @PrimaryKey
//    val id: Long,
//    @Embedded val currentWeatherEntity: CurrentWeatherEntity,
//    @Embedded val hourlyForecastEntity: HourlyForecastWithFavoriteEntity,
//    @Embedded val weeklyForecastEntity: WeeklyForecastWithFavoriteEntity
//
////    @Embedded val hourlyForecastEntity: List<HourlyWeatherEntity>,
////    @Embedded val weeklyForecastEntity: List<DailyWeatherEntity>
//)
