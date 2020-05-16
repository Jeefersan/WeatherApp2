package com.jeefersan.weatherapp.misc

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.jeefersan.data.weatherforecast.datasources.remote.models.Daily
import com.jeefersan.data.weatherforecast.datasources.remote.models.Hourly
import com.jeefersan.weatherapp.MainActivity
import com.jeefersan.weatherapp.R
import kotlin.math.roundToInt

/**
 * Created by JeeferSan on 13-5-20.
 */

const val NOTIFICATION_ID = 1
const val NOTIFICATION_RAIN_ID = 2
const val REQUEST_CODE = 3

const val ACTION_CODE = "action_daily_notification"
const val FAVORITE_ID = "favorite_id"

const val CHANNEL_NAME_DAILY = "Daily"
const val CHANNEL_NAME_RAIN = "Rain"


fun createNotificationChannel(
    context: Context, name: String, importance: Int, description: String
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val channelId = "${context.packageName}-$name"

        Log.d("Notification","NotificationChannel(\n" +
                "                $channelId,\n" +
                "                $name,\n" +
                "                $importance\n" +
                "            )")
        val channel =

            NotificationChannel(
                channelId,
                name,
                importance
            )
                .apply {
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    setDescription(description)
                }
        val notificationManager = context.getSystemService(NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


fun Context.showDailyNotification(daily: Daily, cityName: String, id: Int) {
    val todaysWeather = daily.weather?.first()
    val icon = getOpenWeatherIconRes(todaysWeather?.icon!!)

    val intent = Intent(this, MainActivity::class.java)
    intent.action = ACTION_CODE
    intent.putExtra(FAVORITE_ID, id)


    val pendingIntent =
        PendingIntent.getActivity(
            applicationContext,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

    val builder =
        NotificationCompat.Builder(this, "${applicationContext.packageName}-$CHANNEL_NAME_DAILY")
            .apply {
                setContentTitle("Today's weather for $cityName")
                setAutoCancel(true)
                setSmallIcon(icon)
                setContentText("Feels like ${daily.feelsLike?.day?.roundToInt()}, ${todaysWeather.description}")
                setContentIntent(pendingIntent)
                priority = NotificationCompat.PRIORITY_HIGH
                setDefaults(NotificationCompat.DEFAULT_ALL)
                addAction(
                    R.drawable.ic_access_alarm_black_24dp,
                    "View more details",
                    pendingIntent
                )
            }

    (applicationContext.getSystemService(NotificationManager::class.java) as NotificationManager).notify(
        NOTIFICATION_ID, builder.build()
    )

}

fun Context.showRainNotification(hourlyForecast: List<Hourly>, cityName: String) {
    Log.d("Notification", hourlyForecast.toString())
    val builder =
        NotificationCompat.Builder(this, "${applicationContext.packageName}-$CHANNEL_NAME_RAIN")
            .apply {
                setContentTitle("Rain Alert for $cityName")
                setAutoCancel(true)
                setSmallIcon(R.drawable.ic_warning_red_24dp)
                setContentText(getRainContentText(hourlyForecast))
                priority = NotificationCompat.PRIORITY_MAX
                setVibrate(longArrayOf(2000L))
            }

    (applicationContext.getSystemService(NotificationManager::class.java) as NotificationManager).notify(
        NOTIFICATION_RAIN_ID, builder.build()
    )

}

fun getRainContentText(hourlyForecast: List<Hourly>): String =
    when (hourlyForecast.size) {
        1 -> "${hourlyForecast.first().weather.first().description?.capitalize()} at ${hourlyForecast.first().dt?.toReadableDate()}!"
        else -> "There will be rain from ${hourlyForecast.first().dt?.toReadableDate()} to ${hourlyForecast.last().dt?.toReadableDate()}!"
    }