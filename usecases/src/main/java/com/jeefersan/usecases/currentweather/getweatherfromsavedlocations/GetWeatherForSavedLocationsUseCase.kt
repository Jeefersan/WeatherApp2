package com.jeefersan.usecases.currentweather.getweatherfromsavedlocations

import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */
interface GetWeatherForSavedLocationsUseCase {
    suspend operator fun invoke(): Result<List<CurrentWeather>>
}