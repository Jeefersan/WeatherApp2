package com.jeefersan.usecases.currentweather.getweatherforcurrentlocation

import com.jeefersan.data.unused.location.datasources.LocationProvider
import com.jeefersan.data.unused.currentweather.repositories.WeatherRepository
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */
class GetWeatherForCurrentLocationUseCaseImpl(
    private val weatherRepository: WeatherRepository,
    private val locationProvider: LocationProvider
) :
    GetWeatherForCurrentLocationUseCase {

    override suspend fun invoke(): Result<CurrentWeather> {
        try {
            val currentLocation = kotlin.run {
                val currentLocationResult = locationProvider.getCurrentCoordinates()
                if (currentLocationResult is Result.Failure) return currentLocationResult
                (currentLocationResult as Result.Success).data
            }

            return when (val result =
                weatherRepository.getCurrentWeatherFromCoordinates(currentLocation)) {
                is Result.Success -> Result.Success(result.data)
                else -> result
            }

        } catch (throwable: Throwable) {
            return Result.Failure(throwable)
        }

    }


}