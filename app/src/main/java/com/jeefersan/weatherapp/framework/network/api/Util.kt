package com.jeefersan.weatherapp.framework.network.api

import com.jeefersan.data.weatherforecast.datasources.remote.api.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by JeeferSan on 27-4-20.
 */

fun provideInterceptor() = Interceptor { chain ->
    val url = chain.request().url.newBuilder().addQueryParameter("appid", WEATHER_API_KEY)
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