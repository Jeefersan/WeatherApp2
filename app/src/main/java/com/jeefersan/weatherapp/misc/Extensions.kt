package com.jeefersan.weatherapp.misc

import android.annotation.SuppressLint
import android.location.Location
import android.widget.TextView
import com.jeefersan.domain.Coordinates
import java.time.Instant
import java.time.LocalTime
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

fun Long.toWeekDay(): String {
    return String.format(Locale.getDefault(), "%tA", this * 1000L)
}


fun Long.toTimestamp(value: Long?): Date? {
    return if (value == null) null else Date(value)
}

fun Date.toTimestamp(date: Date?): Long? {
    return date?.time
}

