package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jeefersan.data.weatherforecast.datasources.local.db.models.FavoriteWithForecastEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherWithFavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyForecastWithFavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.WeeklyForecastWithFavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */
@Dao
interface FavoriteWithForecastDao {

    @Transaction
    @Query("SELECT * FROM favorites")
    fun getFavoritesWithForecasts(): List<FavoriteWithForecastEntity>

    @Transaction
    @Query("SELECT * FROM favorites WHERE id = :favoriteId")
    fun getCurrentWeatherByFavoriteId(favoriteId: Long): CurrentWeatherWithFavoriteEntity

    @Transaction
    @Query("SELECT * FROM favorites WHERE id = :favoriteId")
    fun getHourlyForecastByFavoriteId(favoriteId: Long): HourlyForecastWithFavoriteEntity

    @Transaction
    @Query("SELECT * FROM favorites WHERE id = :favoriteId")
    fun getWeeklyForecastByFavoriteId(favoriteId: Long): WeeklyForecastWithFavoriteEntity

}