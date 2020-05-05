package com.jeefersan.data.weatherforecast.repositories

import com.jeefersan.data.currentweather.CurrentWeatherLocalDataSource
import com.jeefersan.data.hourlyforecast.HourlyForecastLocalDataSource
import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSource
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.HourlyForecast
import com.jeefersan.domain.HourlyWeather
import com.jeefersan.util.Result


class WeatherRepositoryImpl(
    private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource,
    private val hourlyForecastLocalDataSource: HourlyForecastLocalDataSource
) :
    WeatherRepository {

    suspend fun getCurrentWeatherByFavoriteId(favoriteId: Long): Result<CurrentWeather> =
        try {
            when (val result =
                currentWeatherLocalDataSource.getCurrentWeatherByFavorite(favoriteId)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(result.data)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    suspend fun getHourlyForecastByFavoriteId(favoriteId: Long): Result<List<HourlyWeather>> =
        try {
            when (val result = hourlyForecastLocalDataSource.getHourlyForecastById(favoriteId)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(result.data)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    suspend fun deleteCurrentWeatherByFavoriteId(favoriteId: Long) : Result<Unit> =
        try {
            currentWeatherLocalDataSource.deleteCurrentWeather(favoriteId)
        }catch (throwable: Throwable){
            Result.Failure(throwable)
        }

}