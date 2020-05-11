package com.jeefersan.data.weatherforecast.repositories

import com.jeefersan.data.weatherforecast.datasources.local.WeatherLocalDataSource
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherRemoteDataSource
import com.jeefersan.domain.*
import com.jeefersan.util.Result

class WeatherRepositoryImpl(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    private var weatherForecastCache: WeatherForecast? = null
    private var coordinatesCache: Coordinates? = null

    override suspend fun getCurrentWeather(
        id: Int,
        coordinates: Coordinates,
        shouldUpdate: Boolean
    ): Result<CurrentWeather> {
        return try {
            if (shouldUpdate) {
                val currentWeather = kotlin.run {
                    val currentWeatherResult = fetchCurrentWeatherFromRemote(coordinates)
                    if (currentWeatherResult is Result.Failure) return currentWeatherResult
                    (currentWeatherResult as Result.Success).data
                }
                when (val result =
                    saveCurrentWeatherToLocal(id = id, currentWeather = currentWeather)) {
                    is Result.Failure -> return result
                    is Result.Success -> Result.Success(currentWeather)
                }

            }

            when (val result = weatherLocalDataSource.getCurrentWeatherById(id)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(result.data)
            }

        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }


    override suspend fun getForecast(
        id: Int,
        coordinates: Coordinates,
        shouldUpdate: Boolean
    ): Result<Forecast> {
        if (shouldUpdate) {
            val forecast = kotlin.run {
                val result = fetchForecastFromRemote(coordinates)
                if (result is Result.Failure) return result
                (result as Result.Success).data
            }
            return when (val result = saveForecastToLocal(id, forecast)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(forecast)
            }
        }

        return when (val result = weatherLocalDataSource.getForecastById(id)) {
            is Result.Failure -> result
            is Result.Success -> Result.Success(result.data)
        }
    }

    override suspend fun getCompleteWeatherForecast(
        id: Int,
        coordinates: Coordinates,
        shouldUpdate: Boolean
    ): Result<WeatherForecast> =
        try {
            val weatherForecast = kotlin.run {
                val weatherForecastResult = fetchCompleteWeatherForecastFromRemote(id, coordinates)
                if (weatherForecastResult is Result.Failure) return weatherForecastResult
                (weatherForecastResult as Result.Success).data
            }
            when (val result =
                saveCompleteWeatherForecastToLocal(
                    id = id,
                    weatherForecast = weatherForecast
                )) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(weatherForecast)
            }

        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


    override suspend fun fetchCompleteWeatherForecastFromRemote(
        id: Int,
        coordinates: Coordinates
    ): Result<WeatherForecast> {
        try {
            if (id == -1 && coordinates == coordinatesCache && weatherForecastCache != null) {
                return Result.Success(weatherForecastCache!!)
            }
            return when (val result = weatherRemoteDataSource.getWeatherForecast(coordinates)) {
                is Result.Success -> {
                    if (id == -1) {
                        coordinatesCache = coordinates
                        weatherForecastCache = result.data
                    }
                    Result.Success(result.data)
                }
                is Result.Failure -> result
            }
        } catch (throwable: Throwable) {
            return Result.Failure(throwable)
        }

    }

    override suspend fun removeAllById(id: Int): Result<Unit> =
        try {
            when (val result = weatherLocalDataSource.removeForecastsById(id)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(Unit)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    private suspend fun fetchCurrentWeatherFromRemote(coordinates: Coordinates): Result<CurrentWeather> =
        weatherRemoteDataSource.getCurrentWeather(coordinates)

    private suspend fun fetchForecastFromRemote(coordinates: Coordinates): Result<Forecast> =
        weatherRemoteDataSource.getForecast(coordinates)

    private suspend fun saveCurrentWeatherToLocal(
        id: Int,
        currentWeather: CurrentWeather
    ): Result<Unit> = try {
        weatherLocalDataSource.saveCurrentWeatherById(id, currentWeather)
        Result.Success(Unit)
    } catch (throwable: Throwable) {
        Result.Failure(throwable)
    }

    private suspend fun saveForecastToLocal(id: Int, forecast: Forecast): Result<Unit> = try {
        weatherLocalDataSource.saveForecastById(id, forecast)
        Result.Success(Unit)
    } catch (throwable: Throwable) {
        Result.Failure(throwable)
    }

    private suspend fun saveCompleteWeatherForecastToLocal(
        id: Int,
        weatherForecast: WeatherForecast
    ): Result<Unit> =
        try {
            val hourlyForecast = weatherForecast.hourlyForecast.hourlyForecast
            val weeklyForecast = weatherForecast.weeklyForecast.weeklyForecast
            weatherLocalDataSource.saveCurrentWeatherById(id, weatherForecast.currentWeather)
            weatherLocalDataSource.saveForecastById(id, (Forecast(hourlyForecast, weeklyForecast)))
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

}