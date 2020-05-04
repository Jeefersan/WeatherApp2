package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyForecastWithFavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.WeeklyForecastWithFavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */

data class FavoriteWithForecastEntity(
    @Embedded val favoriteEntity: FavoriteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    var currentWeatherEntity: CurrentWeatherEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    var weeklyForecast: List<DailyWeatherEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    var hourlyForecast: List<DailyWeatherEntity>

)