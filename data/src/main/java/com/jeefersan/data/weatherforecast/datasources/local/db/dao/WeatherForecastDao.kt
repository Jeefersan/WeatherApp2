package com.jeefersan.data.weatherforecast.datasources.local.db.dao

import androidx.room.*

import com.jeefersan.data.weatherforecast.datasources.local.db.models.WeatherForecastEntity

/**
 * Created by JeeferSan on 25-4-20.
 */

@Dao
interface WeatherForecastDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    @Query("SELECT * FROM weather_forecast WHERE id = :id")
    fun getWeatherForecastById(id: Long): WeatherForecastEntity

//    @Transaction
//    @Query("SELECT * FROM favorites")
//    fun getFavoritesWithForecasts(): List<FavoriteForecastEntity>
//
//


}