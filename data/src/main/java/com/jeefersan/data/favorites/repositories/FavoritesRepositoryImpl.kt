package com.jeefersan.data.favorites.repositories

import com.jeefersan.data.favorites.datasources.local.datasources.FavoritesLocalDataSource
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result

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

    override suspend fun getAllFavorites(): Result<List<Favorite>> =
        try {
            Result.Success(favoritesLocalDataSource.getFavorites()).data
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun removeFavoriteById(favoriteId: Long): Result<Unit> =
        try {
            favoritesLocalDataSource.deleteFavoriteById(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}