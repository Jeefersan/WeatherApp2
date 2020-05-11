package com.jeefersan.weatherapp.presentation.home.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.jeefersan.domain.Location
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastUseCase
import com.jeefersan.usecases.location.getcurrentlocation.GetCurrentLocationUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import com.jeefersan.weatherapp.presentation.home.HomeFragmentDirections
import com.jeefersan.weatherapp.models.*
import com.jeefersan.weatherapp.misc.mapToPresentation
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
    private val getCompleteWeatherForecastUseCase: GetCompleteWeatherForecastUseCase
) : HomeViewModel, BaseViewModel() {

    private val _currentLocationForecast = MutableLiveData<WeatherForecastModel>()
    override val currentLocationForecast: LiveData<WeatherForecastModel> = _currentLocationForecast

    override val currentWeather: LiveData<CurrentWeatherModel> =
        currentLocationForecast.map { it.currentWeatherModel }

    override val hourlyForecast: LiveData<HourlyForecastModel> =
        currentLocationForecast.map { it.hourlyForecast }

    override val weeklyForecast: LiveData<WeeklyForecastModel> =
        currentLocationForecast.map { it.weeklyForecast }

    override val currentLocationLiveData: LiveData<Location> =
        liveData {
            getCurrentLocationUseCase().collect { result ->
                if (result is Result.Success) {
                    Log.d("HomeViewModel", " Success + ${result.data.cityName}")
                    emit(result.data)
                    retrieveWeatherForecast(result.data)
                }
                else{
//                    setStatus(LoadingStatus.ERROR)
                    Log.d("HomeViewModel", "Failure")
                }
            }
        }

    override val isSunset: LiveData<Boolean> = currentWeather.map { currentWeather ->
        System.currentTimeMillis() <= currentWeather.sunset
    }

    init {
        setStatus(LoadingStatus.LOADING)
    }


    private suspend fun retrieveWeatherForecast(location: Location) {
        viewModelScope.launch {
            when (val result = getCompleteWeatherForecastUseCase(location.coordinates)) {
                is Result.Success -> {
                    Log.d("HomeViewModel", "OSucceed")
                    _currentLocationForecast.value = result.data.mapToPresentation()
                    setStatus(LoadingStatus.DONE)
                }
                is Result.Failure -> {
                    Log.d("HomeViewModel", "Failure + ${result.throwable.toString()}")
                    setStatus(LoadingStatus.ERROR)
                }
            }
        }

    }

    override fun onShowForecastButtonClick() {
        navigate(
            HomeFragmentDirections.actionHomeFragmentToWeeklyForecastFragment(
                currentLocationForecast.value!!.weeklyForecast,
                currentLocationLiveData.value!!.cityName
            )
        )
    }
}

