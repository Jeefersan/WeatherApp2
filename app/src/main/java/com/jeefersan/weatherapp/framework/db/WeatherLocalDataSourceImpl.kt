//package com.jeefersan.weatherapp.framework.db
//
//import com.jeefersan.data.unused.currentweather.datasources.local.WeatherLocalDataSource
//import com.jeefersan.domain.CurrentWeather
//
///**
// * Created by JeeferSan on 24-4-20.
// */
//class WeatherLocalDataSourceImpl(private val localDatabase: LocalDatabase) :
//    WeatherLocalDataSource {
//    override suspend fun getWeatherByCityId(cityId: Long): CurrentWeather {
//        return localDatabase.favoriteWeatherForecastDao().getWeatherByCityId(cityId)
//            .mapToWeather()
//    }
//
//}