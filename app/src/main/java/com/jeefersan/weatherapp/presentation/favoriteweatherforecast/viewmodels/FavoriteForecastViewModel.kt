package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.github.matteobattilana.weather.PrecipType
import com.github.matteobattilana.weather.WeatherData
import com.jeefersan.weatherapp.models.CurrentWeatherModel
import com.jeefersan.weatherapp.models.HourlyForecastModel
import com.jeefersan.weatherapp.models.WeeklyForecastModel


/**
 * Created by JeeferSan on 8-5-20.
 */
interface FavoriteForecastViewModel {

    val cityName: LiveData<String>
    val currentWeather: LiveData<CurrentWeatherModel>
    val hourlyForecastModel: LiveData<HourlyForecastModel>
    val weeklyForecastModel: LiveData<WeeklyForecastModel>

    val weatherType: LiveData<PrecipType>

}