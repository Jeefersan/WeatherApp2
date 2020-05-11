package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by JeeferSan on 4-5-20.
 */

@Dao
interface HourlyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateHourlyForecast(hourlyForecast: List<HourlyWeatherEntity>)

    @Query("DELETE FROM hourly_forecast WHERE favorite_id = :favoriteId")
   suspend fun deleteHourlyForecastsById(favoriteId: Int)

    @Query("SELECT * FROM hourly_forecast WHERE favorite_id = :favoriteId ORDER BY timeStamp")
   suspend fun getAllHourlyForecastsById(favoriteId: Int) : List<HourlyWeatherEntity>

    @Query("SELECT * FROM hourly_forecast")
    suspend fun getAllHourlyForecasts() : List<HourlyWeatherEntity>
}



//    @Transaction
//    @Query("SELECT * FROM hourly_forecast WHERE favoriteOwnerId = :favoriteId")
//    fun getHourlyForecastById(vararg favoriteId: Long): HourlyForecastWithFavoriteEntity
//
//
//    // ???
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(hourlyForecastWithFavoriteEntity: HourlyForecastWithFavoriteEntity)
//
//    @Query("DELETE FROM hourly_forecast WHERE favoriteOwnerId = :id")
//    fun deleteHourlyForecastById(id: Long)

