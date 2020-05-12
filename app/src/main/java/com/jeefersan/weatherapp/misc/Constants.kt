package com.jeefersan.weatherapp.misc

import com.jeefersan.weatherapp.R

/**
 * Created by JeeferSan on 26-4-20.
 */

object Constants {

    object Keys {
        const val LOCATION_LAST_UPDATE: String = "location_last_update"
        const val LOCATION_LAST_COORDINATES: String = "location_last_coordinates"
    }

}

const val LOCATION_REQUEST_CODE = 99

const val FOREGROUND_REQUEST_CODE = 88

const val HOUR_INTERVAL = 3600000L

val TOP_LEVEL_DESTINATIONS = setOf(
    R.id.nav_home,
    R.id.nav_favorites,
    R.id.nav_settings
)
