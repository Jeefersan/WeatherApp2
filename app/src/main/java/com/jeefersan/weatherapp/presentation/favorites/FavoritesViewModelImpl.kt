package com.jeefersan.weatherapp.presentation.favorites

import androidx.lifecycle.*
import com.jeefersan.domain.Favorite
import com.jeefersan.usecases.favorites.GetWeatherForecastForFavorites
import com.jeefersan.usecases.favorites.getfavorites.GetFavoritesUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.models.FavoriteForecastModel
import com.jeefersan.weatherapp.models.FavoriteModel
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import com.jeefersan.weatherapp.presentation.home.utils.mapToDomain
import com.jeefersan.weatherapp.presentation.home.utils.mapToPresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by JeeferSan on 25-4-20.
 */

class FavoritesViewModelImpl(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getWeatherForecastForFavorites: GetWeatherForecastForFavorites
) :
    BaseViewModel(), FavoritesViewModel {

    init {
        setStatus(LoadingStatus.LOADING)
    }

    override val favoritesList: LiveData<List<FavoriteModel>> = liveData {
        getFavoritesUseCase().collect { result ->
            if (result is Result.Success) {
                emit(result.data.map { it.mapToPresentation() })
            }
        }
    }


    override val favoriteForecasts: LiveData<List<FavoriteForecastModel>> =
        favoritesList.switchMap { favorites ->
            liveData {
                when (val result = getWeatherForecastForFavorites(favorites.mapToDomain())) {
                    is Result.Failure -> setStatus(LoadingStatus.ERROR)
                    is Result.Success -> {
                        emit(result.data.mapToPresentation())
                        setStatus(LoadingStatus.DONE)
                    }
                }
            }
        }
}





