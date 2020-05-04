package com.jeefersan.data.unused.currentweather.repositories

import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.CurrentWeather
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 20-4-20.
 */

interface WeatherRepository {
    suspend fun getCurrentWeather(): Result<CurrentWeather>

    suspend fun getCurrentWeatherFromCoordinates(coordinates: Coordinates): Result<CurrentWeather>

    suspend fun getMultipleCurrentWeathers(locations: List<Location>): Result<List<CurrentWeather>>
}