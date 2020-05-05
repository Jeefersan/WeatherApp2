package com.jeefersan.data.unused.currentweather.datasources.remote

import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 20-4-20.
 */

interface CurrentWeatherRemoteDataSource {
    suspend fun getWeatherByCoordinates(lat: Double, long: Double): Result<CurrentWeather>


}