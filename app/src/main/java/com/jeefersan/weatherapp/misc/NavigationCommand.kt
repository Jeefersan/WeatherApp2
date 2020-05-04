package com.jeefersan.weatherapp.misc

import androidx.navigation.NavDirections

/**
 * Created by JeeferSan on 23-4-20.
 */

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    object Back: NavigationCommand()

}