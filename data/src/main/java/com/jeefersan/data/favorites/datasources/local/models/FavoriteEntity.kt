package com.jeefersan.data.favorites.datasources.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by JeeferSan on 3-5-20.
 */

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val lastUpdateTime: Long
)


