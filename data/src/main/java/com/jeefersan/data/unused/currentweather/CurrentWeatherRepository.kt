package com.jeefersan.data.unused.currentweather

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