package com.jeefersan.weatherapp.framework.di

import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.jeefersan.weatherapp.framework.network.api.provideInterceptor
import com.jeefersan.weatherapp.framework.network.api.provideOkHttpClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by JeeferSan on 2-5-20.
 */

fun configureNetworkForInstrumentationTest(baseTestApi: String) = module {

    single {
        Retrofit.Builder()
            .baseUrl(baseTestApi)
            .client(provideOkHttpClient(provideInterceptor()))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }
    factory{ get<Retrofit>().create(WeatherApiService::class.java) }
}

