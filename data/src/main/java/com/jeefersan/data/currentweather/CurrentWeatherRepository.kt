package com.jeefersan.data.currentweather

import com.jeefersan.data.unused.currentweather.datasources.remote.CurrentWeatherRemoteDataSource
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToDomain
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 5-5-20.
 */

//class CurrentWeatherRepository(
//    private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource,
//    private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource
//) {
//   suspend fun getCurrentWeatherByFavorite(favoriteId: Long): Result<CurrentWeather> =
//        try {
//            Result.Success(currentWeatherDao.getCurrentWeatherById(favoriteId).mapToDomain())
//        } catch (throwable: Throwable) {
//            Result.Failure(throwable)
//        }
//
//    suspend fun insertOrUpdateCurrentWeather(currentweather: CurrentWeatherEntity): Result<Unit> =
//        try {
//            Result.Success(currentWeatherDao.insertOrUpdateCurrentWeather(currentweather))
//        } catch (throwable: Throwable) {
//            Result.Failure(throwable)
//        }
//
//   suspend fun deleteCurrentWeather(favoriteId: Long): Result<Unit> =
//        try {
//            Result.Success(currentWeatherDao.deleteCurrentWeather(favoriteId))
//        } catch (throwable: Throwable) {
//            Result.Failure(throwable)
//        }
//}