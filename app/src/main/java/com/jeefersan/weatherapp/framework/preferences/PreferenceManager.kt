package com.jeefersan.weatherapp.framework.preferences

import android.content.SharedPreferences
import androidx.core.content.edit


/**
 * Created by JeeferSan on 12-5-20.
 */

class PreferenceManager(private val sharedPreferences: SharedPreferences) {

    fun getLocationTrackingPref(): Boolean =
        sharedPreferences.getBoolean(KEY_FOREGROUND_ENABLED, false)

    fun saveLocationTrackingPref(isForegroundEnabled: Boolean) =
        sharedPreferences.edit { putBoolean(KEY_FOREGROUND_ENABLED, isForegroundEnabled)}

}