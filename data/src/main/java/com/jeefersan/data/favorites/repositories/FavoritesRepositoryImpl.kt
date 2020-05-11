package com.jeefersan.data.favorites.repositories

import com.jeefersan.data.favorites.datasources.local.FavoritesLocalDataSource
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

/**
 * Created by JeeferSan on 20-4-20.
 */
class FavoritesRepositoryImpl(private val favoritesLocalDataSource: FavoritesLocalDataSource) :
    FavoritesRepository {
    override suspend fun addFavorite(favorite: Favorite): Result<Unit> =
        try {
            when (val result = favoritesLocalDataSource.insertFavorite(favorite)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(result.data)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


    override suspend fun setCurrentUpdate(favoriteId: Int): Result<Unit> =
        try {
            favoritesLocalDataSource.setLastCurrentUpdate(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


    override suspend fun setForecastUpdate(favoriteId: Int): Result<Unit> =
        try {
            favoritesLocalDataSource.setLastForecastUpdate(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun getFavoriteById(favoriteId: Int): Result<Favorite> =
        favoritesLocalDataSource.getFavoriteById(favoriteId)


    override suspend fun getAllFavorites(): Result<List<Favorite>> =
        favoritesLocalDataSource.getAllFavorites()

    override suspend fun removeFavoriteById(favoriteId: Int): Result<Unit> =
        try {
            when (val result = favoritesLocalDataSource.deleteFavoriteById(favoriteId)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(Unit)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}