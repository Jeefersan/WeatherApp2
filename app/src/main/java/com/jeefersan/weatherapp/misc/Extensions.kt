package com.jeefersan.weatherapp.misc

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jeefersan.domain.Coordinates
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.models.DailyWeatherModel
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.detail.DailyCustomDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by JeeferSan on 22-4-20.
 */


fun Location.isDistanceGreaterThan5Km(otherLocation: Location): Boolean {
    return (this.distanceTo(otherLocation) / 1000) > 5f
}

fun Location.mapToCoordinates(): Coordinates = Coordinates(
    latitude, longitude
)

fun Long.toHourlyDate(): String {
    @SuppressLint("SimpleDateFormat")
    val sdf = java.text.SimpleDateFormat("kk:mm")
    val date = java.util.Date(this * 1000)
    return sdf.format(date)
}

fun Long.toAbbreviatedWeekDay(): String {
    @SuppressLint("SimpleDateFormat")
    val sdf = java.text.SimpleDateFormat("EEE")
    val date = Date(this * 1000)
    return sdf.format(date)
}

fun Long.toWeekDay(): String {
    return String.format(Locale.getDefault(), "%tA", this * 1000L)
}

fun Location?.toText():String {
    return if (this != null) {
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}


fun Long.toTimestamp(value: Long?): Date? {
    return if (value == null) null else Date(value)
}

fun Date.toTimestamp(date: Date?): Long? {
    return date?.time
}

fun Fragment.showDailyCustomDialog(dailyWeatherModel: DailyWeatherModel) {
    val dialog = DailyCustomDialog(dailyWeatherModel)

    dialog.dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation


    this.activity?.supportFragmentManager?.let { dialog.show(it, null) }
}

fun Context.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context.showKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun Activity.showSnackbar(view: View, message: String) = Snackbar.make(
    view,
    message,
Snackbar.LENGTH_SHORT
)

inline fun <reified T> SharedPreferences.observeKey(key: String, default: T, dispatcher: CoroutineContext = Dispatchers.Default): Flow<T> {
    val flow: Flow<T> = channelFlow {
        offer(getItem(key, default))

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, k ->
            if (key == k) {
                offer(getItem(key, default)!!)
            }
        }

        registerOnSharedPreferenceChangeListener(listener)
        awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
    }
    return flow
        .flowOn(dispatcher)
}

inline fun <reified T> SharedPreferences.getItem(key: String, default: T): T {
    @Suppress("UNCHECKED_CAST")
    return when (default){
        is String -> getString(key, default) as T
        is Int -> getInt(key, default) as T
        is Long -> getLong(key, default) as T
        is Boolean -> getBoolean(key, default) as T
        is Float -> getFloat(key, default) as T
        is Set<*> -> getStringSet(key, default as Set<String>) as T
        is MutableSet<*> -> getStringSet(key, default as MutableSet<String>) as T
        else -> throw IllegalArgumentException("generic type not handle ${T::class.java.name}")
    }
}