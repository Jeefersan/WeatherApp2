package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.WeeklyForecastWithFavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */
interface WeeklyForecastDao {


    @Transaction
    @Query("SELECT * FROM weekly_forecast WHERE id = :favoriteId")
    fun getWeeklyForecastById(favoriteId: Long): WeeklyForecastWithFavoriteEntity


    // ???
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weeklyForecastWithFavoriteEntity: WeeklyForecastWithFavoriteEntity)

    @Query("DELETE FROM weekly_forecast WHERE id = :id")
    fun deleteWeeklyForecastById(id: Long)
}