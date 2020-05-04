package com.jeefersan.data.favorites.datasources.local.db

import androidx.room.*
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.WeatherForecastEntity

/**
 * Created by JeeferSan on 3-5-20.
 */

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): List<FavoriteEntity>


    @Query("DELETE FROM favorites WHERE id = :favoriteId")
    fun deleteFavoriteById(favoriteId: Long)
}