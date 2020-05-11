package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by JeeferSan on 4-5-20.
 */

@Dao
interface WeeklyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertWeeklyForecast(weeklyForecast: List<DailyWeatherEntity>)

    @Query("DELETE FROM weekly_forecast WHERE favorite_id = :favoriteId")
    suspend fun deleteWeeklyForecastById(favoriteId: Int)

    @Query("SELECT * FROM weekly_forecast WHERE favorite_id = :favoriteId ORDER BY date")
    suspend fun getWeeklyForecastById(favoriteId: Int) : List<DailyWeatherEntity>

    @Query("SELECT * FROM weekly_forecast")
    suspend fun getAllDailyForecasts(): List<DailyWeatherEntity>


}


//@Dao
//interface WeeklyForecastDao {
//
//    @Transaction
//    @Query("SELECT * FROM weekly_forecast WHERE favoriteOwnerId = :favoriteId")
//    fun getWeeklyForecastById(vararg favoriteId: Long): WeeklyForecastWithFavoriteEntity
//
//
//    // ???
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(weeklyForecastWithFavoriteEntity: WeeklyForecastWithFavoriteEntity)
//
//    @Query("DELETE FROM weekly_forecast WHERE favoriteOwnerId = :id")
//    fun deleteWeeklyForecastById(id: Long)
//}