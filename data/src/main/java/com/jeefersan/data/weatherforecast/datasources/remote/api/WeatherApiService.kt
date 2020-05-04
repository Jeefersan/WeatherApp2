package com.jeefersan.data.weatherforecast.datasources.remote.api

import com.jeefersan.data.weatherforecast.datasources.remote.models.WeatherResponse
import com.jeefersan.data.weatherforecast.datasources.remote.models.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by JeeferSan on 21-4-20.
 */
interface WeatherApiService {

    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    @GET("onecall")
    suspend fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ) : ForecastResponse


}