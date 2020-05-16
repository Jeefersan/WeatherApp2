package com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity


/**
 * Created by JeeferSan on 4-5-20.
 */

@Entity(
    tableName = "current_weathers", foreignKeys = [
        ForeignKey(
            entity = FavoriteEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = ["favorite_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CurrentWeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "favorite_id")
    val favoriteId: Int,
    val currentTemp: Int?,
    val timestamp: Long?,
    val sunset: Long?,
    val windSpeed: Int?,
    val humidity: Int?,
    val cloudiness: Int?,
    val icon: String?,
    val description: String?
)