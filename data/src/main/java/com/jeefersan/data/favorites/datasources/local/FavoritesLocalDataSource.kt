package com.jeefersan.data.favorites.datasources.local

import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 20-4-20.
 */
interface FavoritesLocalDataSource {

    suspend fun insertFavorite(favorite: Favorite): Result<Unit>

    suspend fun setLastCurrentUpdate(favoriteId: Int): Result<Unit>

    suspend fun setLastForecastUpdate(favoriteId: Int): Result<Unit>

    suspend fun getFavoriteById(favoriteId: Int): Result<Favorite>

    suspend fun getAllFavorites(): Result<List<Favorite>>

    suspend fun deleteFavoriteById(favoriteId: Int): Result<Unit>

}