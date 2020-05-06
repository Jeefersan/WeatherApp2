package com.jeefersan.data.unused.weather

import com.jeefersan.data.unused.currentweather.CurrentWeatherLocalDataSource
import com.jeefersan.data.unused.hourlyforecast.HourlyForecastLocalDataSource
import com.jeefersan.domain.CurrentWeather
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