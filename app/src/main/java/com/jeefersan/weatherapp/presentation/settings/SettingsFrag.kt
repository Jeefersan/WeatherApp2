package com.jeefersan.weatherapp.presentation.settings

import android.app.AlarmManager
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.jeefersan.weatherapp.R

/**
 * Created by JeeferSan on 12-5-20.
 */
class SettingsFrag : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)

        val alarmManager : AlarmManager
    }


}