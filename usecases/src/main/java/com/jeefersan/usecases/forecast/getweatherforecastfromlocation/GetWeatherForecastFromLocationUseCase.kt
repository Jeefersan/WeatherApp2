package com.jeefersan.usecases.forecast.getweatherforecastfromlocation

import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 28-4-20.
 */
interface GetWeatherForecastFromLocationUseCase {
    suspend operator fun invoke(coordinates: Coordinates) : Result<WeatherForecast>
}