package com.jeefersan.weatherapp.framework.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * Created by JeeferSan on 2-5-20.
 */

@FlowPreview
@ExperimentalCoroutinesApi
val appComponent = listOf(
    applicationModule,
    networkModule,
    databaseModule,
    useCaseModule,
    weatherModule,
    favoritesModule,
    viewModelModule,
    sharedPreferencesModule,
    locationModule

)