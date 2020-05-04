package com.jeefersan.data.favorites.datasources.local.datasources

import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.domain.Favorite
import com.jeefersan.domain.FavoriteForecast
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 20-4-20.
 */
interface FavoritesLocalDataSource {

    suspend fun insertFavorite(favorite: Favorite): Result<Unit>

    suspend fun getFavorites(): Result<List<Favorite>>

    suspend fun deleteFavoriteById(favoriteId: Long): Result<Unit>

}