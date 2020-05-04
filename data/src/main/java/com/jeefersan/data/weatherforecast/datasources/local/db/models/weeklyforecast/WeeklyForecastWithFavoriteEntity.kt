package com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast

import androidx.room.*
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity

/**
 * Created by JeeferSan on 3-5-20.
 */



data class WeeklyForecastWithFavoriteEntity(
    @Embedded val favoriteEntity: FavoriteEntity)
{
    @Relation(parentColumn = "id", entityColumn = "id", entity = DailyWeatherEntity::class)

    lateinit var weeklyForecast: List<DailyWeatherEntity>
}

