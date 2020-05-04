package com.jeefersan.weatherapp.presentation.weeklyforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.models.DailyWeatherModel
import com.jeefersan.weatherapp.models.WeeklyForecastModel

/**
 * Created by JeeferSan on 29-4-20.
 */
class WeeklyForecastViewModelImpl(
    forecast: WeeklyForecastModel,
    override val locationName: String
) :
    BaseViewModel(),
    WeeklyForecastViewModel {


    private val _weeklyForecast = MutableLiveData<List<DailyWeatherModel>>()
    override val weeklyForecast: LiveData<List<DailyWeatherModel>> = _weeklyForecast

    init {
        _weeklyForecast.value = forecast.weeklyForecast
    }
}