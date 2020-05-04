package com.jeefersan.weatherapp.presentation.home.viewmodels

import androidx.lifecycle.LiveData
import com.jeefersan.domain.Location
import com.jeefersan.weatherapp.models.*

/**
 * Created by JeeferSan on 23-4-20.
 */

interface HomeViewModel {

    val currentLocationForecast: LiveData<WeatherForecastModel>

    val currentWeather: LiveData<CurrentWeatherModel>
    val hourlyForecast: LiveData<HourlyForecastModel>
    val weeklyForecast: LiveData<WeeklyForecastModel>
    val currentLocationLiveData: LiveData<Location>
    val isSunset: LiveData<Boolean>

    fun onShowForecastButtonClick()

}