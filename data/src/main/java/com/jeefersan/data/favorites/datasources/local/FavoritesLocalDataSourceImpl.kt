package com.jeefersan.data.favorites.datasources.local

import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.favorites.datasources.local.utils.mapToLocal

import com.jeefersan.data.weatherforecast.util.mapToDomain
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result


class FavoritesLocalDataSourceImpl(private val favoritesDao: FavoritesDao) :
    FavoritesLocalDataSource {
    override suspend fun insertFavorite(favorite: Favorite): Result<Unit> =
        try {
            favoritesDao.insertOrUpdateFavorite(favorite.mapToLocal())
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun setLastCurrentUpdate(favoriteId: Int): Result<Unit> =
        try {
            favoritesDao.setLastCurrentUpdate(System.currentTimeMillis(), favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun setLastForecastUpdate(favoriteId: Int): Result<Unit> =
        try {
            favoritesDao.setLastCurrentUpdate(System.currentTimeMillis(), favoriteId)
            Result.Success(Unit)
        }catch (throwable: Throwable){
            Result.Failure(throwable)
        }

    override suspend fun getFavoriteById(favoriteId: Int): Result<Favorite> =
        try {
            val favorite = favoritesDao.getFavoriteById(favoriteId)
            Result.Success(favorite.mapToDomain())
        } catch (throwable: Throwable) {
            Result.Failure(Throwable("can't get favorite by id"))
        }

    override suspend fun getAllFavorites(): Result<List<Favorite>> =
        try {
            Result.Success(favoritesDao.getAllFavorites().map { it.mapToDomain() })
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun deleteFavoriteById(favoriteId: Int): Result<Unit> =
        try {
            favoritesDao.deleteFavoriteById(favoriteId)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}