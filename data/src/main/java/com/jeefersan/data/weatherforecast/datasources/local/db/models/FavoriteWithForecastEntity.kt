package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity


/**
 * Created by JeeferSan on 4-5-20.
 */


class FavoriteWithForecastEntity {
    @Embedded
    lateinit var favoriteEntity: FavoriteEntity

    @Embedded
    lateinit var currentWeatherEntity: CurrentWeatherEntity

    @Embedded
    lateinit var hourlyForecast: List<HourlyWeatherEntity>

    @Embedded
    lateinit var weeklyForecast: List<DailyWeatherEntity>


}