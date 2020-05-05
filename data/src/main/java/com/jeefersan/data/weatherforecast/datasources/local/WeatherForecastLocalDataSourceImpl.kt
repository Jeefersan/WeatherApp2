package com.jeefersan.data.weatherforecast.datasources.local

import com.jeefersan.data.weatherforecast.datasources.local.db.dao.CurrentWeatherDao
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.HourlyForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.WeeklyForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToDailyWeather
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToDomain
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToHourlyWeather
import com.jeefersan.data.weatherforecast.datasources.local.db.utils.mapToLocal
import com.jeefersan.domain.HourlyForecast
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.domain.WeeklyForecast
import com.jeefersan.util.Result

class WeatherForecastLocalDataSourceImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val hourlyForecastDao: HourlyForecastDao,
    private val weeklyForecastDao: WeeklyForecastDao
) :

    WeatherForecastLocalDataSource {

    override suspend fun insertWeatherForecast(weatherForecast: WeatherForecast): Result<Unit> =
        try {
            currentWeatherDao.insertOrUpdateCurrentWeather(weatherForecast.currentWeather.mapToLocal())
            hourlyForecastDao.insertOrUpdateHourlyForecast(weatherForecast.hourlyForecast.hourlyWeatherEntity.map { it.mapToLocal() })
            weeklyForecastDao.insertWeeklyForecast(weatherForecast.weeklyForecast.weeklyForecast.map { it.mapToLocal() })
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun getWeatherForecastById(id: Long): Result<WeatherForecast> =
        try {
            val currentWeather = currentWeatherDao.getCurrentWeatherById(id).mapToDomain()
            val hourlyForecast =
                hourlyForecastDao.getAllHourlyForecastsById(id).map { it.mapToHourlyWeather() }
            val weeklyForecast =
                weeklyForecastDao.getWeeklyForecastById(id).map { it.mapToDailyWeather() }
            Result.Success(
                WeatherForecast(
                    id,
                    currentWeather,
                    HourlyForecast(hourlyForecast),
                    WeeklyForecast(id, weeklyForecast)
                )
            )
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
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