package com.jeefersan.weatherapp.presentation.settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.*
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.framework.configs.ConfigsManager
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by JeeferSan on 12-5-20.
 */
class SettingsFrag : PreferenceFragmentCompat() {
    private val favoritesDao: FavoritesDao by inject()
    private val configsManager: ConfigsManager by inject()

    private lateinit var dailyNotificationSwitch: SwitchPreferenceCompat
    private lateinit var dailyNotificationTimePreference: Preference
    private lateinit var favoriteListPreference: ListPreference
    private lateinit var rainNotificationSwitch: SwitchPreferenceCompat
    private lateinit var intervalDropdownPreference: DropDownPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefs)

        initSwitches()
        initFavorites()
        initTimePicker()
        initUpdateInterval()
        initInfoCategory()
    }

    private fun initSwitches() {
        (findPreference<SwitchPreferenceCompat>(getString(R.string.key_dark_theme)) as SwitchPreferenceCompat).apply {
            isChecked = configsManager.isDarkThemeEnabled()
            setOnPreferenceChangeListener { _, isEnabled ->
                configsManager.setDarkThemeEnabled(isEnabled as Boolean)
                true
            }
        }
        dailyNotificationSwitch =
            findPreference<SwitchPreferenceCompat>(getString(R.string.key_notifications_daily)) as SwitchPreferenceCompat
        dailyNotificationSwitch.apply {
            isChecked =
                configsManager.isNotificationEnabled(getString(R.string.key_notifications_daily))
            setOnPreferenceChangeListener { _, isEnabled ->
                configsManager.setNotificationEnabled(
                    getString(R.string.key_notifications_daily),
                    isEnabled as Boolean
                )
                favoriteListPreference.isVisible = !isChecked
                dailyNotificationTimePreference.isVisible = !isChecked
                true
            }
        }

        rainNotificationSwitch =
            findPreference<SwitchPreferenceCompat>(getString(R.string.key_notifications_rain)) as SwitchPreferenceCompat
        rainNotificationSwitch.apply {
            isChecked =
                configsManager.isNotificationEnabled(getString(R.string.key_notifications_rain))
            setOnPreferenceChangeListener { _, isEnabled ->
                configsManager.setNotificationEnabled(
                    resources.getString(R.string.key_notifications_rain),
                    isEnabled as Boolean
                )
                intervalDropdownPreference.isVisible = !isChecked
                true
            }
        }
    }


    private fun initFavorites() {
        favoriteListPreference =
            preferenceManager.findPreference<ListPreference>(resources.getString(R.string.key_select_favorites)) as ListPreference

        lifecycleScope.launch {
            val favoriteList = favoritesDao.getAllFavorites()
            Log.d("SettingsFrag", "favorites: $favoriteList")
            val entries = favoriteList.map { it.cityName }.toTypedArray()
            val entryValues = favoriteList.map { it.id.toString() }.toTypedArray()
            val id = configsManager.getIdForDailyNotification()
            favoriteListPreference
                .apply {
                    summary = entry
                    setEntries(entries)
                    setEntryValues(entryValues)
                    isVisible =
                        configsManager.isNotificationEnabled(getString(R.string.key_notifications_daily))
                    setOnPreferenceChangeListener { preference, newValue ->
                        summary = entries[entryValues.indexOf(newValue)]
                        dailyNotificationTimePreference.isEnabled = true
                        configsManager.setIdForDailyNotification(newValue.toString().toInt())
                        true
                    }
                    if (favoriteList.isNullOrEmpty()) {
                        summary =
                            "No favorites to select from"
                        isEnabled = false
                    }
                    if (id != -1) {
                        val savedEntry = entries[entryValues.indexOf(id.toString())]
                        summary = savedEntry
                        setDefaultValue(savedEntry)
                    }
                }
        }
    }

    private fun initUpdateInterval() {
        intervalDropdownPreference =
            findPreference<DropDownPreference>(getString(R.string.key_rain_interval)) as DropDownPreference
        intervalDropdownPreference.apply {
            isVisible =
                configsManager.isNotificationEnabled(getString(R.string.key_notifications_rain))
            summary = entry
            isEnabled = isVisible
            setOnPreferenceChangeListener { preference, newValue ->
                configsManager.setIntervalForRainNotification(newValue.toString().toInt())
                summary = entries[entryValues.indexOf(newValue)]
                true
            }
        }
    }


    private fun initTimePicker() {
        dailyNotificationTimePreference =
            findPreference(getString(R.string.key_notification_time))!!

        val calendar = Calendar.getInstance(Locale.getDefault())
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        dailyNotificationTimePreference.apply {
            isVisible = dailyNotificationSwitch.isChecked
            setOnPreferenceClickListener {
                TimePickerDialog(
                    requireContext(),
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        var hourString = ""
                        if (hourOfDay < 10) hourString += "0$hourOfDay" else hourString += hourOfDay
                        var minuteString = ""
                        if (minute < 10) minuteString += "0$minute" else minuteString += minute
                        val timeString = "$hourString:$minuteString"
                        configsManager.setTimeForDailyNotification(timeString)
                        dailyNotificationTimePreference.summary = timeString
                    }, hour, minutes, true
                )
                    .show()
                true
            }
            summary = configsManager.getTimeForDailyNotification()
        }
    }

    private fun initInfoCategory() {

        findPreference<Preference>(getString(R.string.key_about_this_app))
            ?.setOnPreferenceClickListener {
                findNavController().navigate(SettingsFragDirections.actionNavSettingsToAboutFragment())
                true
            }


    }
}
