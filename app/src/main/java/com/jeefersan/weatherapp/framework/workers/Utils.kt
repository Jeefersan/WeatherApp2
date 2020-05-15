package com.jeefersan.weatherapp.framework.workers

import androidx.work.*
import com.jeefersan.weatherapp.misc.FAVORITE_KEY
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by JeeferSan on 13-5-20.
 */

const val MAX_NUMBER_OF_RETRY = 3
const val DAILY_NOTIFICATION_TAG = "daily_notification_tag"
const val RAIN_NOTIFICATION_TAG = "rain_notification_tag"

fun getNetworkConstraints() = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

fun getTimeDiff(timeArray: IntArray): Long {
    val currentDate = Calendar.getInstance()
    val dueDate = Calendar.getInstance()
        .apply {
            set(Calendar.HOUR_OF_DAY, timeArray[0])
            set(Calendar.MINUTE, timeArray[1])
        }

    if (dueDate.before(currentDate)) {
        dueDate.add(Calendar.HOUR_OF_DAY, 24)
    }
    return dueDate.timeInMillis - currentDate.timeInMillis
}

fun getDailyNotificationRequest(timeString: String) =
    OneTimeWorkRequestBuilder<DailyNotificationWorker>()
        .addTag(DAILY_NOTIFICATION_TAG)
        .setInitialDelay(getTimeDiff(timeString.timeStringToIntArray()), TimeUnit.MILLISECONDS)
        .setConstraints(getNetworkConstraints())
        .build()

fun getRainNotificationRequest(interval: Int) =
    PeriodicWorkRequestBuilder<RainNotificationWorker>(interval.toLong(), TimeUnit.HOURS)
        .addTag(RAIN_NOTIFICATION_TAG)
        .setConstraints(getNetworkConstraints())
        .build()

fun String.timeStringToIntArray(): IntArray =
    this.split(":").map { it.toInt() }.toIntArray()

fun createInputData(id: Int): Data =
    Data.Builder()
        .putInt(FAVORITE_KEY, id)
        .build()
