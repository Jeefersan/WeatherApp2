package com.jeefersan.data.favorites.repositories

import com.jeefersan.data.favorites.datasources.local.datasources.FavoritesLocalDataSource
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by JeeferSan on 20-4-20.
 */
class FavoritesRepositoryImpl(private val favoritesLocalDataSource: FavoritesLocalDataSource) :
    FavoritesRepository {
    override suspend fun addFavorite(favorite: Favorite): Result<Unit> =
        try {
            Result.Success(favoritesLocalDataSource.insertFavorite(favorite)).data
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun getAllFavorites(): Flow<Result<List<Favorite>>> =
        favoritesLocalDataSource.getFavorites().map {
            Result.Success(it)
        }

    override suspend fun removeFavoriteById(favoriteId: Long): Result<Unit> =
        try {
            favoritesLocalDataSource.deleteFavoriteById(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}