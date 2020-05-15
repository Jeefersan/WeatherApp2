package com.jeefersan.weatherapp.framework.workers

import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.framework.configs.ConfigsManager
import com.jeefersan.weatherapp.misc.CHANNEL_NAME_DAILY
import com.jeefersan.weatherapp.misc.CHANNEL_NAME_RAIN
import com.jeefersan.weatherapp.misc.createNotificationChannel
import com.jeefersan.weatherapp.presentation.base.BaseActivity
import org.koin.android.ext.android.get

/**
 * Created by JeeferSan on 14-5-20.
 */
abstract class WorkerActivity : BaseActivity() {

    private val workManager by lazy { WorkManager.getInstance(this) }
    private val configsManager: ConfigsManager = get()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configsManager.isDailyNotificationEnabled.observe(this, Observer { isEnabled ->
            if (isEnabled) createNotificationChannel(CHANNEL_NAME_DAILY) else stopNotifications(
                CHANNEL_NAME_DAILY
            )
        })
        configsManager.isRainNotificationEnabled.observe(this, Observer { isEnabled ->
            if (isEnabled) {
                createNotificationChannel(CHANNEL_NAME_RAIN)
                startRainNotificationWorker()
            } else stopNotifications(
                CHANNEL_NAME_RAIN
            )
        })
        configsManager.dailyNotificationTimeString.observe(this, Observer { time ->
            startDailyNotificationWorker(time)
        })
        configsManager.isDarkThemeSelected.observe(this, Observer { isDark ->
            setTheme(isDark)
        })
    }

    private fun setTheme(isDark: Boolean) {
        if(isDark) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun createNotificationChannel(name: String) {
        when (name) {
            CHANNEL_NAME_DAILY -> {

                createNotificationChannel(
                    this,
                    name,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT,
                    resources.getString(R.string.channel_daily_description)
                )
            }

            CHANNEL_NAME_RAIN -> createNotificationChannel(
                this, name, NotificationManagerCompat.IMPORTANCE_MAX,
                resources.getString(R.string.channel_rain_description)
            )
        }
    }

    private fun startDailyNotificationWorker(timeString: String) {
        workManager.enqueueUniqueWork(
            DAILY_NOTIFICATION_TAG, ExistingWorkPolicy.REPLACE,
            getDailyNotificationRequest(timeString)
        )
    }

    private fun startRainNotificationWorker() {
        val interval = configsManager.getIntervalForRainNotification()
        Log.d("WorkerActivity", "startRainNotificationWorker called. Interval is $interval")
        workManager.enqueueUniquePeriodicWork(
            RAIN_NOTIFICATION_TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            getRainNotificationRequest(interval)
        )
    }


    private fun stopNotifications(name: String) {

        (getSystemService(NotificationManager::class.java) as NotificationManager).deleteNotificationChannel(
            "${application.packageName}-$name"
        )

        when (name) {
            CHANNEL_NAME_DAILY -> stopDailyNotificationWorker()
            CHANNEL_NAME_RAIN -> stopRainNotificationWorker()
        }
    }


    private fun stopDailyNotificationWorker() =
        workManager.cancelUniqueWork(DAILY_NOTIFICATION_TAG)


    private fun stopRainNotificationWorker() = workManager.cancelUniqueWork(RAIN_NOTIFICATION_TAG)
}