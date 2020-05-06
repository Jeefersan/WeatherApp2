package com.jeefersan.weatherapp.presentation.favorites.viewmodels

import androidx.lifecycle.LiveData
import com.jeefersan.weatherapp.models.FavoriteForecastModel
import com.jeefersan.weatherapp.models.FavoriteModel

/**
 * Created by JeeferSan on 25-4-20.
 */
interface FavoritesViewModel {
    val favoritesList: LiveData<List<FavoriteModel>>
    val favoriteForecasts: LiveData<List<FavoriteForecastModel>>

    fun onFabClick()
}