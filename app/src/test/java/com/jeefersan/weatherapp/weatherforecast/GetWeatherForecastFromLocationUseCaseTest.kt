package com.jeefersan.weatherapp.weatherforecast

import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.usecases.forecast.getweatherforecastfromlocation.GetWeatherForecastFromLocationUseCase
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 2-5-20.
 */
class GetWeatherForecastFromLocationUseCaseTest : GetWeatherForecastFromLocationUseCase {
    override suspend fun invoke(location: Location): Result<WeatherForecast> {
        TODO("Not yet implemented")
    }
}