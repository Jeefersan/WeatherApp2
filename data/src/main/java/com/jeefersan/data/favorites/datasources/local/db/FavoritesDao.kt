package com.jeefersan.data.favorites.datasources.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity

/**
 * Created by JeeferSan on 3-5-20.
 */

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites WHERE id = :favoriteId")
    suspend fun getFavoriteById(favoriteId: Int): FavoriteEntity

    @Query("SELECT * FROM favorites ORDER BY id")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Query("UPDATE favorites SET lastCurrentUpdate=:lastCurrentUpdate WHERE id = :favoriteId ")
    suspend fun setLastCurrentUpdate(lastCurrentUpdate: Long, favoriteId: Int)

    @Query("UPDATE favorites SET lastForecastUpdate=:lastForecastUpdate WHERE id = :favoriteId ")
    suspend fun setLastForecastUpdate(lastForecastUpdate: Long, favoriteId: Int)

    @Query("DELETE FROM favorites WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}