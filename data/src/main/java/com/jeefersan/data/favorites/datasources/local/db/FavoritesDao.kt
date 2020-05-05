package com.jeefersan.data.favorites.datasources.local.db

import androidx.room.*
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 3-5-20.
 */

@Dao
abstract class FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOrUpdateFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    abstract fun getAllFavorites(): Flow<List<FavoriteEntity>>


    @Query("DELETE FROM favorites WHERE id = :favoriteId")
    abstract fun deleteFavoriteById(favoriteId: Long)
}