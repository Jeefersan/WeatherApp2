package com.jeefersan.data.favorites.datasources.local.datasources

import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.favorites.datasources.local.utils.mapToFavorite
import com.jeefersan.data.favorites.datasources.local.utils.mapToFavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToDomain
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesLocalDataSourceImpl(private val favoritesDao: FavoritesDao) :
    FavoritesLocalDataSource {
    override suspend fun insertFavorite(favorite: Favorite): Result<Unit> =
        try {
            favoritesDao.insertOrUpdateFavorite(favorite.mapToFavoriteEntity())
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


    override suspend fun getFavorites(): Flow<List<Favorite>> =
        favoritesDao.getAllFavorites().map { it.map { it.mapToDomain() } }


    override suspend fun deleteFavoriteById(favoriteId: Long): Result<Unit> =
        try {
            favoritesDao.deleteFavoriteById(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}