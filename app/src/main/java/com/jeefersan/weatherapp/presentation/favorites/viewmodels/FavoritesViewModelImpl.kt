package com.jeefersan.weatherapp.presentation.favorites.viewmodels

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.usecases.favorites.GetCurrentWeathersForFavoritesUseCase
import com.jeefersan.usecases.favorites.addfavorite.AddFavoriteUseCase
import com.jeefersan.usecases.favorites.removefavorite.RemoveFavoriteUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.mapToPresentation
import com.jeefersan.weatherapp.models.FavoriteCurrentWeatherModel
import com.jeefersan.weatherapp.models.LocationModel
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import com.jeefersan.weatherapp.presentation.favorites.FavoritesFragmentDirections
import kotlinx.coroutines.launch

/**
 * Created by JeeferSan on 25-4-20.
 */

class FavoritesViewModelImpl(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getCurrentWeathersForFavoritesUseCase: GetCurrentWeathersForFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) :
    BaseViewModel(),
    FavoritesViewModel {

    override val isLoading = ObservableBoolean()
    private val _favoriteCurrentWeatherModels = MutableLiveData<List<FavoriteCurrentWeatherModel>>()
    override val favoriteCurrentWeatherModels: LiveData<List<FavoriteCurrentWeatherModel>> =
        _favoriteCurrentWeatherModels

    init {
        viewModelScope.launch {
            retrieveForecasts()
        }
    }


    private suspend fun retrieveForecasts() {
        setStatus(LoadingStatus.LOADING)
        viewModelScope.launch {
            when (val result = getCurrentWeathersForFavoritesUseCase()) {
                is Result.Failure -> {
                    setStatus(LoadingStatus.ERROR)
                    Log.d("FavoritesViewModel", "retrieveForecasts failed + ${result.throwable.toString()}")
                }
                is Result.Success -> {
                    setStatus(LoadingStatus.DONE)
                    _favoriteCurrentWeatherModels.value =
                        result.data.map { it.mapToPresentation() }
                    isLoading.set(false)
                }
            }
        }
    }


    override fun onFabClick() {
        navigate(FavoritesFragmentDirections.actionNavFavoritesToSearchFragment())
    }

    fun onNewLocationSelected(location: LocationModel) =
        addNewFavorite(location)


    private fun addNewFavorite(location: LocationModel) {
        Log.d("FavoriteViewModel", "location is $location")
        viewModelScope.launch {
            when (val result = addFavoriteUseCase(
                Location(
                    cityName = location.cityName,
                    country = location.country,
                    coordinates = Coordinates(location.lat, location.long)
                )
            )) {
                is Result.Failure -> {
                    showSnackbar("There was a problem with adding a new location to your favorites")
                    Log.d("FavoritesViewModel", " Failure = ${result.throwable}")
                }
                is Result.Success -> {
                    showSnackbar("Added ${location.cityName} to your favorites")
                    Log.d(
                        "FavoritesViewModel",
                        "New favorite location = ${location.cityName}"
                    )
                    retrieveForecasts()
                }
            }
        }
    }

    fun onRefresh() = viewModelScope.launch { retrieveForecasts() }

    override fun onShowDetailsClick(id: Int) =
        navigate(FavoritesFragmentDirections.actionNavFavoritesToNavFavoriteForecast(id))


    override fun onRemoveClick(id: Int) {
        val favoriteToBeRemoved = _favoriteCurrentWeatherModels.value!!.find { it.id == id }!!
        viewModelScope.launch {
            when (val result = removeFavoriteUseCase(id)) {
                is Result.Failure -> Log.d(
                    "Favorites", "removeFavorite failed + ${result.throwable}"
                )
                is Result.Success -> {
                    val newList = _favoriteCurrentWeatherModels.value!!.minus(favoriteToBeRemoved)
                    _favoriteCurrentWeatherModels.value = newList
                    showSnackbar("Removed ${favoriteToBeRemoved.cityName} from your list.")
                }
            }
        }
    }
}

