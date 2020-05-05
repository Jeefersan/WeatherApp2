package com.jeefersan.data.weatherforecast.datasources.remote


import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.data.weatherforecast.util.mapToWeatherForecast
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 29-4-20.
 */
class WeatherForecastRemoteDataSourceImpl(private val weatherApi: WeatherApiService) :
    WeatherForecastRemoteDataSource {
    override suspend fun getWeatherForecast(coordinates: Coordinates): Result<WeatherForecast> =
        try {
            val result = weatherApi.getForecastByCoordinates(
                coordinates.lat,
                coordinates.long
            )
            Result.Success(result.mapToWeatherForecast())

        } catch (throwable: Throwable) {
            Result.Failure(throwable)

        }

}

