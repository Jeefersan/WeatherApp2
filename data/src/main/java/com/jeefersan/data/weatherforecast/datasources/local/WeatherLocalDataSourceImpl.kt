package com.jeefersan.data.weatherforecast.datasources.local

import com.jeefersan.data.weatherforecast.datasources.local.db.dao.CurrentWeatherDao
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.HourlyForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.WeeklyForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToDailyWeather
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToHourlyWeather
import com.jeefersan.data.weatherforecast.util.mapToDomain
import com.jeefersan.data.weatherforecast.util.mapToLocalWithId
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.Forecast
import com.jeefersan.util.Result

class WeatherLocalDataSourceImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val hourlyForecastDao: HourlyForecastDao,
    private val weeklyForecastDao: WeeklyForecastDao
) : WeatherLocalDataSource {
    override suspend fun getCurrentWeatherById(id: Int): Result<CurrentWeather> =
        try {
            Result.Success(currentWeatherDao.getCurrentWeatherById(id).mapToDomain())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun removeForecastsById(id: Int): Result<Unit> =
        try {
            currentWeatherDao.deleteCurrentWeather(id)
            hourlyForecastDao.deleteHourlyForecastsById(id)
            weeklyForecastDao.deleteWeeklyForecastById(id)
            Result.Success(Unit)
        }catch (throwable: Throwable){
            Result.Failure(throwable)
        }

    override suspend fun getForecastById(id: Int): Result<Forecast> =
        try {
            val hourlyForecast = hourlyForecastDao.getAllHourlyForecastsById(id)
            val weeklyForecast = weeklyForecastDao.getWeeklyForecastById(id)
            Result.Success(
                Forecast(
                    hourlyForecast.map { it.mapToHourlyWeather() },
                    weeklyForecast.map { it.mapToDailyWeather() })
            )
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun saveCurrentWeatherById(
        id: Int,
        currentWeather: CurrentWeather
    ): Result<Unit> = try {
        currentWeatherDao.deleteCurrentWeather(id)
        currentWeatherDao.insertOrUpdateCurrentWeather(currentWeather.mapToLocalWithId(id))
        Result.Success(Unit)
    } catch (throwable: Throwable) {
        Result.Failure(throwable)
    }

    override suspend fun saveForecastById(id: Int, forecast: Forecast): Result<Unit> =
        try {
            hourlyForecastDao.deleteHourlyForecastsById(id)
            weeklyForecastDao.deleteWeeklyForecastById(id)
            hourlyForecastDao.insertOrUpdateHourlyForecast(forecast.hourlyForecast.map {
                it.mapToLocalWithId(
                    id
                )
            })
            weeklyForecastDao.insertWeeklyForecast(forecast.weeklyForecast.map {
                it.mapToLocalWithId(
                    id
                )
            })
            Result.Success(Unit)
        } catch (throwble: Throwable) {
            Result.Failure(throwble)
        }
}