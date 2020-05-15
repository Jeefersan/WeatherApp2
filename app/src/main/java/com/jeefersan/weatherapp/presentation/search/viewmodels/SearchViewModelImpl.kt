package com.jeefersan.weatherapp.presentation.search.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jeefersan.domain.Location
import com.jeefersan.usecases.location.retrievelocations.RetrieveLocationsUseCase
import com.jeefersan.usecases.location.searchlocations.SearchLocationsUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.models.LocationModel
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.search.SearchFragmentDirections
import kotlinx.coroutines.flow.collect

class SearchViewModelImpl(private val searchLocationsUseCase: SearchLocationsUseCase,
                          private val retrieveLocationsUseCase: RetrieveLocationsUseCase
) :
    SearchViewModel, BaseViewModel() {

    override val searchInputText = MutableLiveData<String>()

    override val locationSuggestions: LiveData<List<Location>> =
        searchInputText.switchMap { input ->
            liveData {
                searchLocationsUseCase(input)
                retrieveLocationsUseCase()
                    .collect {
                        if(it is Result.Success){
                            emit(it.data)
                        }
                        else{
                            emit(emptyList<Location>())
                        }
                    }
            }
        }


    override fun onLocationSelect(location: Location) {
        Log.d("SearchViewModel", "new location is $location")
      navigate(SearchFragmentDirections.actionSearchFragmentToNavFavorites(LocationModel(location.cityName,location.country?: "",location.coordinates.lat, location.coordinates.long )))
    }
}

