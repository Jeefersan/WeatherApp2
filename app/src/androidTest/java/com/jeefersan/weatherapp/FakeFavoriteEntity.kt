package com.jeefersan.weatherappFavoriteEntity

import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.tmp.LocationEntity

/**
 * Created by JeeferSan on 3-5-20.
 */


    fun provideFakeFavoriteEntity(): FavoriteEntity {
        val locationEntity =
            LocationEntity(cityName = "Rotterdam", latitude = 5.0265, longitude = 3.4569)
        val lastUpdateTime = System.currentTimeMillis()

        val currentWeatherEntity = CurrentWeatherEntity(
            22,
            856595625, 54953256, 4, 50, 70, 12345, 45454,"Amsterdam", "10d", "cloudy"
        )

        val hourlyForecastEntity =
            HourlyForecastEntity(listOf(HourlyWeatherEntity(23, 858566, "50d", 40, 5, 322)))

        val weeklyForecastEntity =
            WeeklyForecastEntity(123, listOf(DailyWeatherEntity(15, 20, 5484955, 5, 50, "50d", "sun")))

        val weatherForecastEntity = WeatherForecastEntity(99, currentWeatherEntity, hourlyForecastEntity, weeklyForecastEntity)

        return FavoriteEntity(
            123,
            locationEntity.cityName,
            locationEntity.latitude,
            locationEntity.longitude,
            lastUpdateTime
        )

    }
