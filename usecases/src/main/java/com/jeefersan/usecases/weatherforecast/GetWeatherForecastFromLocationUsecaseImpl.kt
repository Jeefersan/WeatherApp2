package com.jeefersan.usecases.weatherforecast

import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.usecases.weatherforecast.GetWeatherForecastFromLocationUseCase
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 28-4-20.
 */


class GetWeatherForecastFromLocationUsecaseImpl(
    private val weatherForecastRepository: WeatherForecastRepository
) : GetWeatherForecastFromLocationUseCase {
    override suspend fun invoke(coordinates: Coordinates): Result<WeatherForecast> =
        try {
            when (val result = weatherForecastRepository.getWeatherForecastFromRemote(coordinates)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Failure -> result
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}