package com.jeefersan.weatherapp.misc

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jeefersan.domain.Coordinates
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.models.DailyWeatherModel
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.detail.DailyCustomDialog
import java.util.*

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

fun Int.toReadableDate(): String {
    @SuppressLint("SimpleDateFormat")
    val sdf = java.text.SimpleDateFormat("EEE, kk:mm")
    val date = Date(this.toLong() * 1000)
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

fun Location?.toText(): String {
    return if (this != null) {
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}

fun Fragment.showSnackbar(view: View, message: String) = Snackbar.make(
    view,
    message,
    Snackbar.LENGTH_SHORT
)


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




fun Activity.showSnackbar(view: View, message: String) = Snackbar.make(
    view,
    message,
    Snackbar.LENGTH_SHORT
)

