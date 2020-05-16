package com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */

@Entity(
    tableName = "weekly_forecast"
    ,
    foreignKeys = [ForeignKey(
        entity = FavoriteEntity::class,
        parentColumns = ["id"],
        childColumns = ["favorite_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DailyWeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "favorite_id")
    val favoriteId: Int,
    val minTemp: Int?,
    val maxTemp: Int?,
    val date: Long?,
    val wind: Int?,
    val humidity: Int?,
    val icon: String?,
    val description: String?
)