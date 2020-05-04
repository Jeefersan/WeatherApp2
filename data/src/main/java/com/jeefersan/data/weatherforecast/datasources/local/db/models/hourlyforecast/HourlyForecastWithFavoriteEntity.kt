package com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast

import androidx.room.*
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity



data class HourlyForecastWithFavoriteEntity(
    @Embedded val favoriteEntity: FavoriteEntity
) {
    @Relation(parentColumn = "id", entityColumn = "id", entity = HourlyWeatherEntity::class)
    lateinit var hourlyForecast: List<HourlyWeatherEntity>
}

