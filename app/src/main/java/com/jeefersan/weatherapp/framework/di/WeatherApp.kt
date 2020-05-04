package com.jeefersan.weatherapp.framework.di

import androidx.multidex.MultiDexApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.android.*
import org.koin.core.context.startKoin


/**
 * Created by JeeferSan on 23-4-20.
 */
open class WeatherApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initKoin()

    }

    @ExperimentalCoroutinesApi
    private fun initKoin() {
        startKoin {
            androidContext(this@WeatherApp)
            modules(
                provideDependency()
            )
        }
    }

    open fun provideDependency() = appComponent

}