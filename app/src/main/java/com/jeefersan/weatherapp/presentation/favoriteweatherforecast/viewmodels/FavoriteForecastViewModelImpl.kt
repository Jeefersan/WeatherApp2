package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastForFavoriteUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.SingleLiveEvent
import com.jeefersan.weatherapp.misc.mapToDailyWeatherModel
import com.jeefersan.weatherapp.misc.mapToHourlyWeatherModel
import com.jeefersan.weatherapp.misc.mapToWeatherModel
import com.jeefersan.weatherapp.models.CurrentWeatherModel
import com.jeefersan.weatherapp.models.DailyWeatherModel
import com.jeefersan.weatherapp.models.HourlyWeatherModel
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

    private val _hourlyForecastModel = MutableLiveData<List<HourlyWeatherModel>>()
    override val hourlyForecastModel: LiveData<List<HourlyWeatherModel>> = _hourlyForecastModel

    private val _weeklyForecast = MutableLiveData<List<DailyWeatherModel>>()
    override val weeklyForecastModel: LiveData<List<DailyWeatherModel>> = _weeklyForecast

    override val showDialog = SingleLiveEvent<DailyWeatherModel>()

    init {
        setStatus(LoadingStatus.LOADING)
        viewModelScope.launch {
            retrieveForecast()
        }
    }

    private suspend fun retrieveForecast() {
        when (val result = getCompleteWeatherForecastForFavoriteUseCase(id)) {
            is Result.Failure -> {
                setStatus(LoadingStatus.ERROR)
                Log.d("fav", "failed value = ${result.throwable}")
            }
            is Result.Success -> {
                result.data.apply {
                    _cityName.value = favorite.cityName
                    _currentWeather.value = currentWeather.mapToWeatherModel()
                    _hourlyForecastModel.value =
                        hourlyForecast.hourlyForecast.map { it.mapToHourlyWeatherModel() }
                    _weeklyForecast.value =
                        weeklyForecast.weeklyForecast.map { it.mapToDailyWeatherModel() }
                }
                Log.d("fav", "success value = ${result.data}")
                setStatus(LoadingStatus.DONE)
            }
        }
    }

    override fun onDailyWeatherclick(position: Int) {
        showDialog.value = weeklyForecastModel.value?.get(position)
    }
}