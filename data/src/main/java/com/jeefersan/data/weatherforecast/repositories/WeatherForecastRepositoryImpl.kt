package com.jeefersan.data.weatherforecast.repositories

import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSource
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherForecastRemoteDataSource
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 29-4-20.
 */
class WeatherForecastRepositoryImpl(
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource,
    private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource
) :
    WeatherForecastRepository {

    override suspend fun insertOrUpdate(weatherForecast: WeatherForecast): Result<Unit> =
        weatherForecastLocalDataSource.insertWeatherForecast(weatherForecast)

    override suspend fun getWeatherForecastFromRemote(coordinates: Coordinates): Result<WeatherForecast> =
        try {
            when (val result = weatherForecastRemoteDataSource.getWeatherForecast(coordinates)) {
                is Result.Success -> Result.Success(result.data)
                else -> result
            }

        } catch (throwable: Throwable) {
            Result.Failure(Throwable("WeatherForecast remote failure"))
        }

    override suspend fun getWeatherForecastByIdFromLocal(id: Long): Result<WeatherForecast> =
        try {
            when(val result = weatherForecastLocalDataSource.getWeatherForecastById(id)){
                is Result.Failure -> result
                is Result.Success -> Result.Success(result.data)
            }
        } catch (throwable: Throwable){
            Result.Failure(throwable)
        }

    private fun shouldUpdate() : Boolean{
        TODO()
    }
}

