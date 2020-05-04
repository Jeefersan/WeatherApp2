package com.jeefersan.data.favorites.datasources.local.db

import androidx.room.*
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.WeatherForecastEntity

/**
 * Created by JeeferSan on 3-5-20.
 */

@Dao
interface FavoritesDao {

    @Insert
    fun insertOrUpdateFavorite(favoriteEntity: FavoriteEntity)

    @Transaction
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): List<FavoriteEntity>


    @Query("DELETE FROM favorites WHERE favoriteId = :favoriteId")
    fun deleteFavoriteById(favoriteId: Long)
}