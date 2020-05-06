package com.jeefersan.weatherapp.presentation.search.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeefersan.domain.Location

/**
 * Created by JeeferSan on 5-5-20.
 */
interface SearchViewModel {

    val searchInputText : MutableLiveData<String>

    val locationSuggestions : LiveData<List<Location>>

    fun onLocationSelect(location: Location)

}