package com.jeefersan.usecases.forecast.getweatherforecastfromlocation

import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 28-4-20.
 */


class GetWeatherForecastFromLocationUsecaseImpl(
    private val weatherForecastRepository: WeatherForecastRepository
) : GetWeatherForecastFromLocationUseCase {
    override suspend fun invoke(location: Location): Result<WeatherForecast> =
        try {
            when (val result = weatherForecastRepository.getWeatherForecastFromRemote(location)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Failure -> result
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}