package com.jeefersan.weatherapp.presentation.favorites.viewmodels

import androidx.lifecycle.*
import com.jeefersan.usecases.favorites.GetWeatherForecastForFavorites
import com.jeefersan.usecases.favorites.getfavorites.GetFavoritesUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.models.FavoriteForecastModel
import com.jeefersan.weatherapp.models.FavoriteModel
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import com.jeefersan.weatherapp.misc.mapToDomain
import com.jeefersan.weatherapp.misc.mapToPresentation
import com.jeefersan.weatherapp.presentation.favorites.FavoritesFragmentDirections
import kotlinx.coroutines.flow.collect

/**
 * Created by JeeferSan on 25-4-20.
 */

class FavoritesViewModelImpl(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getWeatherForecastForFavorites: GetWeatherForecastForFavorites
) :
    BaseViewModel(),
    FavoritesViewModel {

    init {
        setStatus(LoadingStatus.LOADING)
    }

    override val favoritesList: LiveData<List<FavoriteModel>> = liveData {
        emit(emptyList())
        getFavoritesUseCase().collect { result ->
            if (result is Result.Success) {
                emit(result.data.map { it.mapToPresentation() })
                setStatus(LoadingStatus.DONE)
            } else {
                emit(emptyList())
                setStatus(LoadingStatus.ERROR)
            }
        }
    }


    override val favoriteForecasts: LiveData<List<FavoriteForecastModel>> =
        favoritesList.switchMap { favorites ->
            liveData {
                when (val result = getWeatherForecastForFavorites(favorites.mapToDomain())) {
                    is Result.Failure -> {
                        setStatus(LoadingStatus.ERROR)
                        emit(emptyList())
                    }
                    is Result.Success -> {
                        emit(result.data.mapToPresentation())
                        setStatus(LoadingStatus.DONE)
                    }
                }
            }
        }

    override fun onFabClick() {
        navigate(FavoritesFragmentDirections.actionNavFavoritesToSearchFragment())
    }
}





