package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherWithFavoriteEntity


/**
 * Created by JeeferSan on 4-5-20.
 */

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM current_weathers WHERE favorite_id = :favoriteId LIMIT 1")
    abstract fun getCurrentWeatherById(favoriteId: Long): CurrentWeatherEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Query("DELETE FROM current_weathers WHERE favorite_id = :favoriteId")
    abstract fun deleteCurrentWeather(favoriteId: Long)

}