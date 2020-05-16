package com.jeefersan.weatherapp.framework.configs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.misc.SingleLiveEvent


/**
 * Created by JeeferSan on 12-5-20.
 */

class ConfigsManager(
    private val sharedPreferences: SharedPreferences,
    private val context: Context
) {

    val isDailyNotificationEnabled = SingleLiveEvent<Boolean>()
    val isRainNotificationEnabled = SingleLiveEvent<Boolean>()

    val dailyNotificationTimeString = SingleLiveEvent<String>()

    private val _isDarkThemeSelected = MutableLiveData<Boolean>()
    val isDarkThemeSelected : LiveData<Boolean> = _isDarkThemeSelected

    init {
        _isDarkThemeSelected.value = isDarkThemeEnabled()
    }

    fun getSharedPreferences() = sharedPreferences

    fun isNotificationEnabled(key: String) =
        sharedPreferences.getBoolean(key, false)

    fun setNotificationEnabled(key: String, isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(key, isEnabled)
            .apply()
        when (key) {
            context.resources.getString(R.string.key_notifications_daily) -> {
                isDailyNotificationEnabled.value = isEnabled
            }
            context.resources.getString(R.string.key_notifications_rain) -> {
                isRainNotificationEnabled.value = isEnabled
            }
        }
    }

    fun getIdForDailyNotification() =
        sharedPreferences.getInt(context.resources.getString(R.string.key_favorite_id), -1)

    fun setIdForDailyNotification(id: Int) {
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.key_favorite_id), id)
            .apply()
    }

    fun setTimeForDailyNotification(timeString: String) {
        sharedPreferences.edit()
            .putString(context.resources.getString(R.string.key_notification_time), timeString)
            .apply()
        dailyNotificationTimeString.value = timeString
        Log.d("ConfigsManager", "setTimeForDailyNotification called")
    }

    fun getTimeForDailyNotification() =
        sharedPreferences.getString(context.resources.getString(R.string.key_notification_time), "")

    fun getIntervalForRainNotification() =
        sharedPreferences.getInt(context.resources.getString(R.string.key_rain_interval), 3)

    fun setIntervalForRainNotification(interval: Int) {
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.key_rain_interval), interval).apply()
        isRainNotificationEnabled.value = true
    }

    fun setDarkThemeEnabled(isDark: Boolean){
        sharedPreferences.edit().putBoolean(context.resources.getString(R.string.key_dark_theme), isDark).apply()
        _isDarkThemeSelected.value = isDark
    }

    fun isDarkThemeEnabled()= sharedPreferences.getBoolean(context.resources.getString(R.string.key_dark_theme), true)



}
