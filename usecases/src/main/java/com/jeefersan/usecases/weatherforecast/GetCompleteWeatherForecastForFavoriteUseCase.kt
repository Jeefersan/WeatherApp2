package com.jeefersan.usecases.weatherforecast

import com.jeefersan.domain.FavoriteWeatherForecast
import com.jeefersan.domain.WeatherForecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 9-5-20.
 */
interface GetCompleteWeatherForecastForFavoriteUseCase {

    suspend operator fun invoke(id: Int) : Result<FavoriteWeatherForecast>

}