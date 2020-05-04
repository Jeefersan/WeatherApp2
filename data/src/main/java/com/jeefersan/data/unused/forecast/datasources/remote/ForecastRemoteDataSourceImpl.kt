package com.jeefersan.data.unused.forecast.datasources.remote

import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.data.weatherforecast.datasources.remote.util.mapToForecast
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Forecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 28-4-20.
 */

class ForecastRemoteDataSourceImpl
    (
    private val weatherApi: WeatherApiService
) : ForecastRemoteDataSource {
    override suspend fun getByCoordinates(coordinates: Coordinates): Result<Forecast> =
        try {
            val response = weatherApi.getForecastByCoordinates(coordinates.lat, coordinates.long)
            Result.Success(response.mapToForecast())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun getByCityId(id: Long): Result<Forecast> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Result<List<Forecast>> {
        TODO("Not yet implemented")
    }
}