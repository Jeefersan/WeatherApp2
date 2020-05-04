package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity

/**
 * Created by JeeferSan on 4-5-20.
 */
interface CurrentWeatherDao {


    @Transaction
    @Query("SELECT * FROM current_weather WHERE id = :favoriteId")
    fun getCurrentWeatherById(favoriteId: Long): CurrentWeatherEntity


    // ???
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeatherEntity: CurrentWeatherEntity)

    @Query("DELETE FROM current_weather WHERE id = :id")
    fun deleteCurrentWeatherById(id: Long)

}