package com.jeefersan.weatherapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeefersan.domain.Location
import com.jeefersan.weatherapp.misc.SingleLiveEvent

/**
 * Created by JeeferSan on 5-5-20.
 */
interface SearchViewModel {

    val searchInputText : MutableLiveData<String>

    val locationSuggestions : MutableLiveData<List<Location>>

    val onSelect : SingleLiveEvent<Unit>

}