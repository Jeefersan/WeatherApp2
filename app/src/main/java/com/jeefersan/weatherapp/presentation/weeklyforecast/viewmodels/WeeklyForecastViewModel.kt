package com.jeefersan.weatherapp.presentation.weeklyforecast.viewmodels

import androidx.lifecycle.LiveData
import com.jeefersan.weatherapp.models.DailyWeatherModel

/**
 * Created by JeeferSan on 29-4-20.
 */
interface WeeklyForecastViewModel {
    val locationName: String
    val weeklyForecast: LiveData<List<DailyWeatherModel>>
}