package com.jeefersan.weatherapp.framework.di

import org.junit.Assert.*
import org.koin.core.module.Module

/**
 * Created by JeeferSan on 2-5-20.
 */
class WeatherAppTest : WeatherApp() {
    override fun provideDependency(): List<Module> = listOf(roomTestModule)
}