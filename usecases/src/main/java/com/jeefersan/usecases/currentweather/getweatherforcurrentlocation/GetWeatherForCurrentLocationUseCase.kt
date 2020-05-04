package com.jeefersan.usecases.currentweather.getweatherforcurrentlocation

import com.jeefersan.domain.CurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 20-4-20.
 */
interface GetWeatherForCurrentLocationUseCase {
    suspend operator fun invoke(): Result<CurrentWeather>
}