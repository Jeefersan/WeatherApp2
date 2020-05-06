package com.jeefersan.weatherapp.presentation.search

import androidx.lifecycle.MutableLiveData
import com.jeefersan.domain.Location
import com.jeefersan.usecases.location.SearchLocationsUseCase
import com.jeefersan.weatherapp.misc.SingleLiveEvent
import com.jeefersan.weatherapp.presentation.base.BaseViewModel

class SearchViewModelImpl(private val searchLocationsUseCase: SearchLocationsUseCase) :
    SearchViewModel, BaseViewModel() {

    override val searchInputText = MutableLiveData<String>()

    override val locationSuggestions = MutableLiveData<List<Location>>()

    override val onSelect = SingleLiveEvent<Unit>()

}