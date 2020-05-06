package com.jeefersan.weatherapp.presentation.search.viewmodels

import androidx.lifecycle.*
import com.jeefersan.domain.Location
import com.jeefersan.usecases.location.retrievelocations.RetrieveLocationsUseCase
import com.jeefersan.usecases.location.searchlocations.SearchLocationsUseCase
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.mapToFavoriteModel
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
        navigate(SearchFragmentDirections.actionSearchFragmentToNavFavorites(location.mapToFavoriteModel()))
    }
}

//override val locationSuggestions: LiveData<List<Location>> =
//    searchInputText.switchMap { input ->
//        liveData {
//            searchLocationsUseCase(input)
//                .collect {
//                    if (it is Result.Success) {
//                        emit(it.data)
//                    } else {
//                        emit(emptyList<Location>())
//                    }
//                }
//        }
//    }
