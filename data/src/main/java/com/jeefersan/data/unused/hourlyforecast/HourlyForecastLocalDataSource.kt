package com.jeefersan.data.unused.hourlyforecast

import com.jeefersan.data.weatherforecast.datasources.local.db.dao.HourlyForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToHourlyWeather
import com.jeefersan.domain.HourlyWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 5-5-20.
 */

class HourlyForecastLocalDataSource(private val hourlyForecastDao: HourlyForecastDao) {
    suspend fun getHourlyForecastById(favoriteId: Long): Result<List<HourlyWeather>> =
        try {
            Result.Success(
                hourlyForecastDao.getAllHourlyForecastsById(favoriteId)
                    .map { it.mapToHourlyWeather() })
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}