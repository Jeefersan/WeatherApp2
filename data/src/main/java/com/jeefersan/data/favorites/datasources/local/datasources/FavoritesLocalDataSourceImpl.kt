package com.jeefersan.data.favorites.datasources.local.datasources

import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.favorites.datasources.local.utils.mapToFavorite
import com.jeefersan.data.favorites.datasources.local.utils.mapToFavoriteEntity
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result

class FavoritesLocalDataSourceImpl(private val favoritesDao: FavoritesDao) :
    FavoritesLocalDataSource {
    override suspend fun insertFavorite(favorite: Favorite): Result<Unit> =
        try {
            favoritesDao.insertOrUpdateFavorite(favorite.mapToFavoriteEntity())
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


    override suspend fun getFavorites(): Result<List<Favorite>> =
        try {
            Result.Success(favoritesDao.getAllFavorites().map { it.mapToFavorite() })
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun deleteFavoriteById(favoriteId: Long): Result<Unit> =
        try {
            favoritesDao.deleteFavoriteById(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}