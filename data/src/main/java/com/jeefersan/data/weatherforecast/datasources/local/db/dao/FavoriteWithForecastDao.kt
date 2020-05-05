package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction


/**
 * Created by JeeferSan on 4-5-20.
 */
@Dao
interface FavoriteWithForecastDao {

//    @Transaction
//    @Query("SELECT * FROM favorites")
//    fun getFavoritesWithForecasts(): List<FavoriteWithForecastEntity>
//
//    @Transaction
//    @Query("SELECT * FROM favorites WHERE favoriteId = :favoriteId")
//    fun getCurrentWeatherByFavoriteId(favoriteId: Long): CurrentWeatherWithFavoriteEntity
//
//    @Transaction
//    @Query("SELECT * FROM favorites WHERE favoriteId = :favoriteId")
//    fun getHourlyForecastByFavoriteId(favoriteId: Long): HourlyForecastWithFavoriteEntity
//
//    @Transaction
//    @Query("SELECT * FROM favorites WHERE favoriteId = :favoriteId")
//    fun getWeeklyForecastByFavoriteId(favoriteId: Long): WeeklyForecastWithFavoriteEntity
//@Transaction
//@Query("SELECT * FROM favorites")
//fun getFavoritesWithForecasts(): List<FavoriteWithForecastEntity>

}