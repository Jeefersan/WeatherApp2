package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.matteobattilana.weather.PrecipType
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastForFavoriteUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.getPrecipType
import com.jeefersan.weatherapp.misc.mapToHourlyForecastModel
import com.jeefersan.weatherapp.misc.mapToWeatherModel
import com.jeefersan.weatherapp.misc.mapToWeeklyForecastModel
import com.jeefersan.weatherapp.models.CurrentWeatherModel
import com.jeefersan.weatherapp.models.HourlyForecastModel
import com.jeefersan.weatherapp.models.WeeklyForecastModel
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import kotlinx.coroutines.launch

class FavoriteForecastViewModelImpl(
    private val getCompleteWeatherForecastForFavoriteUseCase: GetCompleteWeatherForecastForFavoriteUseCase,
    private val id: Int
) :
    FavoriteForecastViewModel, BaseViewModel() {

    private val _cityName = MutableLiveData<String>()
    override val cityName: LiveData<String> = _cityName

    private val _currentWeather = MutableLiveData<CurrentWeatherModel>()
    override val currentWeather: LiveData<CurrentWeatherModel> = _currentWeather

    private val _hourlyForecast = MutableLiveData<HourlyForecastModel>()
    override val hourlyForecastModel: LiveData<HourlyForecastModel> = _hourlyForecast

    private val _weeklyForecast = MutableLiveData<WeeklyForecastModel>()
    override val weeklyForecastModel: LiveData<WeeklyForecastModel> = _weeklyForecast

    override val weatherType: LiveData<PrecipType> = currentWeather.map { getPrecipType(it.icon) }


    init {
        setStatus(LoadingStatus.LOADING)
        viewModelScope.launch {
            retrieveForecast()
        }
    }

    private suspend fun retrieveForecast() {
        when (val result = getCompleteWeatherForecastForFavoriteUseCase(id)) {
            is Result.Failure -> {setStatus(LoadingStatus.ERROR)
                Log.d("fav", "failed value = ${result.throwable}")}
            is Result.Success -> {
                result.data.apply {
                    _cityName.value = favorite.cityName
                    _currentWeather.value = currentWeather.mapToWeatherModel()
                    _hourlyForecast.value = hourlyForecast.mapToHourlyForecastModel()
                    _weeklyForecast.value = weeklyForecast.mapToWeeklyForecastModel()
                }
                Log.d("fav", "success value = ${result.data}")
                setStatus(LoadingStatus.DONE)
            }
        }
    }


}