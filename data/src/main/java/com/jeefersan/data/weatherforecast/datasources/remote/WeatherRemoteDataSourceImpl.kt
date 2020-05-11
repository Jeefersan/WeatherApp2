package com.jeefersan.data.weatherforecast.datasources.remote



import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.data.weatherforecast.datasources.remote.util.mapToWeather
import com.jeefersan.data.weatherforecast.util.mapToForecast
import com.jeefersan.data.weatherforecast.util.mapToWeatherForecast
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.Forecast
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 29-4-20.
 */
class WeatherRemoteDataSourceImpl(private val weatherApi: WeatherApiService) :
    WeatherRemoteDataSource {
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

    override suspend fun getCurrentWeather(coordinates: Coordinates): Result<CurrentWeather> =
        try {
            val response = weatherApi.getWeatherByCoordinates(
                coordinates.lat,
                coordinates.long
            )
            Result.Success(response.mapToWeather())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    override suspend fun getForecast(coordinates: Coordinates): Result<Forecast> =
        try {
            val response = weatherApi.getForecastByCoordinates(
                coordinates.lat,
                coordinates.long
            )
            Result.Success(response.mapToForecast())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


}

