package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels


import androidx.lifecycle.LiveData
import com.jeefersan.weatherapp.misc.SingleLiveEvent
import com.jeefersan.weatherapp.models.*


/**
 * Created by JeeferSan on 8-5-20.
 */
interface FavoriteForecastViewModel {

    val cityName: LiveData<String>
    val currentWeather: LiveData<CurrentWeatherModel>
    val hourlyForecastModel: LiveData<List<HourlyWeatherModel>>
    val weeklyForecastModel: LiveData<List<DailyWeatherModel>>

    val showDialog: SingleLiveEvent<DailyWeatherModel>

    fun onDailyWeatherclick(position: Int)

}