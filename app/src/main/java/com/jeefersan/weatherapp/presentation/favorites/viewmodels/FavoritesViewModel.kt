package com.jeefersan.weatherapp.presentation.favorites.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import com.jeefersan.weatherapp.models.FavoriteCurrentWeatherModel

/**
 * Created by JeeferSan on 25-4-20.
 */
interface FavoritesViewModel {

    val favoriteCurrentWeatherModels: LiveData<List<FavoriteCurrentWeatherModel>>

    val isLoading: ObservableBoolean

    fun onFabClick()

    fun onShowDetailsClick(id: Int)

    fun onRemoveClick(id: Int)
}