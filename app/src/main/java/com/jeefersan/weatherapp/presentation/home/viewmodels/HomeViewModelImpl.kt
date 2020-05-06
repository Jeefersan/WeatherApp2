package com.jeefersan.weatherapp.presentation.home.viewmodels

import androidx.lifecycle.*
import com.jeefersan.domain.Location
import com.jeefersan.usecases.forecast.getweatherforecastfromlocation.GetWeatherForecastFromLocationUseCase
import com.jeefersan.usecases.location.GetCurrentLocationUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import com.jeefersan.weatherapp.presentation.home.HomeFragmentDirections
import com.jeefersan.weatherapp.models.*
import com.jeefersan.weatherapp.presentation.home.utils.mapToPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by JeeferSan on 24-4-20.
 */

@ExperimentalCoroutinesApi
class HomeViewModelImpl
    (
//    private val getCurrentWeatherForCurrentLocationUseCase: GetWeatherForCurrentLocationUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getWeatherForecastFromLocationUseCase: GetWeatherForecastFromLocationUseCase
) : HomeViewModel, BaseViewModel() {

    private val _currentLocationForecast = MutableLiveData<WeatherForecastModel>()
    override val currentLocationForecast: LiveData<WeatherForecastModel> = _currentLocationForecast

    override val currentWeather: LiveData<CurrentWeatherModel> =
        currentLocationForecast.map { it.currentWeatherModel }

    override val hourlyForecast: LiveData<HourlyForecastModel> =
        currentLocationForecast.map { it.hourlyForecast }

    override val weeklyForecast: LiveData<WeeklyForecastModel> =
        currentLocationForecast.map { it.weeklyForecast }

    private val _currentLocationLiveData = MutableLiveData<Location>()
    override val currentLocationLiveData: LiveData<Location> = _currentLocationLiveData

    override val isSunset: LiveData<Boolean> = currentWeather.map { currentWeather ->
        currentWeather.timestamp >= currentWeather.sunset
    }

    init {
        setStatus(LoadingStatus.LOADING)
        viewModelScope.launch {
            getCurrentLocationUseCase().collect {
                if (it is Result.Success) {
                    _currentLocationLiveData.value = it.data
                    retrieveWeatherForecast(it.data)
                } else {
                    setStatus(LoadingStatus.ERROR)
                }
            }
        }
    }


    private suspend fun retrieveWeatherForecast(location: Location) {
        viewModelScope.launch {
            when (val result = getWeatherForecastFromLocationUseCase(location.coordinates)) {
                is Result.Success -> {
                    _currentLocationForecast.value = result.data.mapToPresentation()
//                    _currentWeather.value = result.data.currentWeather.mapToWeatherModel()
//                    _hourlyForecast.value =
//                        result.data.hourlyForecast.map { it.mapToHourlyWeatherModel() }
//                    _weeklyForecast.value =
//                        result.data.weeklyForecast.map { it.mapToDailyWeatherModel() }
                    setStatus(LoadingStatus.DONE)
                }
                else -> {
                    setStatus(LoadingStatus.ERROR)
                }
            }
        }

    }

    override fun onShowForecastButtonClick() {
        navigate(
            HomeFragmentDirections.actionHomeFragmentToWeeklyForecastFragment(
                currentLocationForecast.value!!.weeklyForecast,
                currentLocationLiveData.value!!.cityName!!
            )
        )
    }
}

//TODO: remove current, hourly and weekly

