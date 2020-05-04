package com.jeefersan.weatherapp.framework.di

import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by JeeferSan on 2-5-20.
 */

@ExperimentalCoroutinesApi
val appComponent = listOf(
    applicationModule,
    networkModule,
    databaseModule,
    useCaseModule,
    weatherModule,
    viewModelModule,
    sharedPreferencesModule,
    flowModule

)