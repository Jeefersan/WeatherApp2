package com.jeefersan.weatherapp.presentation.about

import com.jeefersan.weatherapp.misc.SingleLiveEvent
import com.jeefersan.weatherapp.presentation.base.BaseViewModel

/**
 * Created by JeeferSan on 16-5-20.
 */

class AboutViewModelImpl : BaseViewModel(), AboutViewModel {
    override val isEmailClicked = SingleLiveEvent<Unit>()

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