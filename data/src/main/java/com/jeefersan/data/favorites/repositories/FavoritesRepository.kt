package com.jeefersan.data.favorites.repositories

import com.jeefersan.domain.Favorite
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 20-4-20.
 */
interface FavoritesRepository {

    suspend fun addFavorite(favorite: Favorite): Result<Unit>

    suspend fun setCurrentUpdate(favoriteId: Int): Result<Unit>

    suspend fun setForecastUpdate(favoriteId: Int): Result<Unit>

    suspend fun getFavoriteById(favoriteId: Int): Result<Favorite>

    suspend fun getAllFavorites(): Result<List<Favorite>>

    suspend fun removeFavoriteById(favoriteId: Int): Result<Unit>

}