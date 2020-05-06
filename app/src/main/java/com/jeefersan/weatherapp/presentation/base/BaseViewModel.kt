package com.jeefersan.weatherapp.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.jeefersan.weatherapp.misc.NavigationCommand
import com.jeefersan.weatherapp.misc.SingleLiveEvent

/**
 * Created by JeeferSan on 23-4-20.
 */

enum class LoadingStatus { LOADING, ERROR, DONE }

abstract class BaseViewModel : ViewModel() {
    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus = _loadingStatus

    val navigation = SingleLiveEvent<NavigationCommand>()
    val snackbar = SingleLiveEvent<String>()

    fun setStatus(status: LoadingStatus){
        _loadingStatus.value = status
    }

    fun navigate(directions: NavDirections){
        navigation.postValue(NavigationCommand.To(directions))
    }

    fun showSnackbar(message: String) = snackbar.postValue(message)

}