package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyForecastWithFavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */

@Dao
interface HourlyForecastDao {

    @Transaction
    @Query("SELECT * FROM hourly_forecast WHERE id = :favoriteId")
    fun getHourlyForecastById(favoriteId: Long): HourlyForecastWithFavoriteEntity


    // ???
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hourlyForecastWithFavoriteEntity: HourlyForecastWithFavoriteEntity)

    @Query("DELETE FROM hourly_forecast WHERE id = :id")
    fun deleteHourlyForecastById(id: Long)

}