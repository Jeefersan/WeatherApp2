package com.jeefersan.weatherapp.framework.di

import com.algolia.search.client.ClientPlaces
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by JeeferSan on 6-5-20.
 */

fun providePlacesClient(): ClientPlaces {
    val client = ClientPlaces(
        ApplicationID("plA2HD70HUT8"), APIKey("94134bf29dd613c3f1052eb7888d8158")
    )
    return client
}

fun provideInterceptor() = Interceptor { chain ->
    val url = chain.request().url.newBuilder().addQueryParameter("appid",
        WEATHER_API_KEY
    )
        .build()
    val request = chain.request()
        .newBuilder()
        .url(url)
        .build()
    chain.proceed(request)
}

fun provideOkHttpClient(interceptor: Interceptor) =
    OkHttpClient().newBuilder().addInterceptor(interceptor).build()

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .client(client)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
}

fun provideApiService(retrofit: Retrofit): WeatherApiService =
    retrofit.create(WeatherApiService::class.java)

const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val WEATHER_API_KEY = "53dbdc6dc33c76c3ee1472abba72fdb3"

