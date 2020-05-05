package com.jeefersan.data.favorites.datasources.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by JeeferSan on 3-5-20.
 */

@Entity(tableName = "favorites", indices = [Index("cityName", unique = true)])
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val lastUpdateTime: Long
)


