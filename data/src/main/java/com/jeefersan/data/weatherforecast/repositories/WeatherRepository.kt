package com.jeefersan.data.weatherforecast.repositories

import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.Forecast
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 9-5-20.
 */
interface WeatherRepository {

    suspend fun getCurrentWeather(
        id: Int,
        coordinates: Coordinates,
        shouldUpdate: Boolean
    ): Result<CurrentWeather>

    suspend fun getForecast(
        id: Int,
        coordinates: Coordinates,
        shouldUpdate: Boolean
    ): Result<Forecast>

    suspend fun getCompleteWeatherForecast(
        id: Int,
        coordinates: Coordinates,
        shouldUpdate: Boolean
    ) : Result<WeatherForecast>

    suspend fun fetchCompleteWeatherForecastFromRemote(id: Int, coordinates: Coordinates): Result<WeatherForecast>

    suspend fun removeAllById(id: Int) : Result<Unit>


}