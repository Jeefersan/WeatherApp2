package com.jeefersan.weatherapp.presentation.about

import com.jeefersan.weatherapp.misc.SingleLiveEvent

/**
 * Created by JeeferSan on 16-5-20.
 */
interface AboutViewModel {

    val isEmailClicked: SingleLiveEvent<Unit>

    fun onFindOutClick()
    fun onEmailClick()
    fun onGithubClick()


}