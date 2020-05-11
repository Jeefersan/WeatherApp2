package com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */

@Entity(
    tableName = "hourly_forecast"
    , foreignKeys = [
        ForeignKey(
            entity = FavoriteEntity::class,
            parentColumns = ["id"],
            childColumns = ["favorite_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HourlyWeatherEntity(
    @PrimaryKey
    @ColumnInfo(name="favorite_id")
    val favoriteId: Int,
    val temperature: Int,
    val timeStamp: Long,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Int,
    val weatherCode: Int
)