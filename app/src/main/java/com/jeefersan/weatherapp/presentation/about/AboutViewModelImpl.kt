package com.jeefersan.weatherapp.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeefersan.weatherapp.BuildConfig
import com.jeefersan.weatherapp.misc.SingleLiveEvent
import com.jeefersan.weatherapp.presentation.base.BaseViewModel

/**
 * Created by JeeferSan on 16-5-20.
 */

class AboutViewModelImpl : BaseViewModel(), AboutViewModel {

    private val _versionName = MutableLiveData<String>()
    override val versionName: LiveData<String> = _versionName

    override val isEmailClicked = SingleLiveEvent<Unit>()

    init {
        _versionName.value = BuildConfig.VERSION_NAME
    }

    override fun onFindOutClick() {
        val uri = "https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html"
        navigate(AboutFragmentDirections.actionNavAboutToNavWeb(uri))
    }

    override fun onEmailClick() =
        isEmailClicked.call()


    override fun onGithubClick() {
        val uri = "https://github.com/jeefersan/"
        navigate(AboutFragmentDirections.actionNavAboutToNavWeb(uri))
    }


}