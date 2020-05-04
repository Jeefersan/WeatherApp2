package com.jeefersan.weatherapp.weatherforecast

import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.domain.Location
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 2-5-20.
 */
class WeatherForecastRepositoryTest : WeatherForecastRepository {

    override suspend fun getWeatherForecastFromRemote(location: Location): Result<WeatherForecast> {
        TODO("Not yet implemented")
    }
}