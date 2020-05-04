package com.jeefersan.data.weatherforecast.datasources.local

import com.jeefersan.data.weatherforecast.datasources.local.db.dao.FavoriteWithForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.WeatherForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToWeatherForecast
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToWeatherForecastEntity
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

class WeatherForecastLocalDataSourceImpl(
//    private val weatherForecastDao: WeatherForecastDao
private val favoriteWithForecastDao: FavoriteWithForecastDao
) :

    WeatherForecastLocalDataSource {

    //    override suspend fun insertWeatherForecast(weatherForecast: WeatherForecast): Result<Unit> =
    override suspend fun insertWeatherForecast(weatherForecast: WeatherForecast): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherForecastById(id: Long): Result<WeatherForecast> {
        TODO("Not yet implemented")
    }
//        try {
//            Result.Success(weatherForecastDao.insertOrUpdateWeatherForecast(weatherForecast.mapToWeatherForecastEntity()))
//        }catch (throwable: Throwable){
//            Result.Failure(throwable)
//        }
//
//    override suspend fun getWeatherForecastById(id: Long): Result<WeatherForecast> =
//        try {
//            Result.Success(weatherForecastDao.getWeatherForecastById(id).mapToWeatherForecast())
//        } catch (throwable: Throwable) {
//            Result.Failure(throwable)
//        }
}