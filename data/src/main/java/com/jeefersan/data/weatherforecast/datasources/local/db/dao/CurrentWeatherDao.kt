package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity


/**
 * Created by JeeferSan on 4-5-20.
 */

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM current_weathers WHERE favorite_id = :favoriteId LIMIT 1")
    suspend fun getCurrentWeatherById(favoriteId: Int): CurrentWeatherEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Query("DELETE FROM current_weathers WHERE favorite_id = :favoriteId")
    suspend fun deleteCurrentWeather(favoriteId: Int)

    @Query("SELECT * FROM current_weathers")
    suspend fun getAllFromCurrentWeathers() : List<CurrentWeatherEntity>

}