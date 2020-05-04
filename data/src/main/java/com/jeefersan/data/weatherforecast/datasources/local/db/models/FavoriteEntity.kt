package com.jeefersan.data.weatherforecast.datasources.local.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by JeeferSan on 4-5-20.
 */

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val favoriteId: Long,
val cityName: String)