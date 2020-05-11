package com.jeefersan.usecases.weatherforecast

import com.jeefersan.data.weatherforecast.repositories.WeatherRepository
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 28-4-20.
 */


class GetCompleteWeatherForecastUseCaseUsecaseImpl(
    private val weatherRepository: WeatherRepository
) : GetCompleteWeatherForecastUseCase {
    override suspend fun invoke(coordinates: Coordinates): Result<WeatherForecast> =
        try {
            when (val result =
                weatherRepository.fetchCompleteWeatherForecastFromRemote(-1, coordinates)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Failure -> result
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}