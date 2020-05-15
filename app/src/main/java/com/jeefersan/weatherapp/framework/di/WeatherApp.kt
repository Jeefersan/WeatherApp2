package com.jeefersan.weatherapp.framework.di

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import com.jeefersan.weatherapp.framework.workers.MyWorkerFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created by JeeferSan on 23-4-20.
 */
@FlowPreview
@ExperimentalCoroutinesApi
open class WeatherApp : MultiDexApplication(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }


    private fun initKoin() {
        startKoin {
            androidContext(this@WeatherApp)
            modules(
                provideDependency()
            )
        }
    }

    open fun provideDependency() = appComponent

    override fun getWorkManagerConfiguration(): Configuration {
        val workerFactory = DelegatingWorkerFactory()
        workerFactory.addFactory(MyWorkerFactory(get(), get(), get(), get()))

        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }


}