//package com.jeefersan.weatherapp.weatherforecast
//
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
//import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
//import com.jeefersan.data.weatherforecast.datasources.local.db.dao.CurrentWeatherDao
//import com.jeefersan.data.weatherforecast.datasources.local.db.dao.FavoriteEntityDao
//import com.jeefersan.data.weatherforecast.datasources.local.db.dao.FavoriteWithForecastDao
//import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
//
//import com.jeefersan.weatherapp.framework.db.LocalDatabase
//import com.jeefersan.weatherapp.framework.di.roomTestModule
//import org.junit.After
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.koin.core.context.loadKoinModules
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.inject
//
///**
// * Created by JeeferSan on 5-5-20.
// */
//
//@RunWith(AndroidJUnit4::class)
//class DaoTest : KoinTest {
//
//    val localDatabase: LocalDatabase by inject()
//    val favoritesDao: FavoritesDao by inject()
//
//    val currentWeatherDao: CurrentWeatherDao by inject()
//
//
//    @Before
//    fun before() {
//        loadKoinModules(roomTestModule)
//    }
//
//    @After
//    fun after() {
//        localDatabase.close()
//        stopKoin()
//    }
//
//    @Test
//    fun testReadAndWrite() {
//
//        val favoriteEntity = FavoriteEntity(
//            id = 0,
//            cityName = "Rotterdam",
//            latitude = 2.0505,
//            longitude = 3.5656,
//            lastUpdateTime = System.currentTimeMillis()
//        )
//
//        favoritesDao.insertOrUpdateFavorite(favoriteEntity)
//
//        val currentWeatherEntity = CurrentWeatherEntity(
//            favoriteEntity.id,
//            30,
//            System.currentTimeMillis(),
//            35353535,
//            5, 6, 64, "ok", "cloudy"
//        )
//
//        currentWeatherDao.insertOrUpdateCurrentWeather(currentWeatherEntity)
//
//        val result = currentWeatherDao.getCurrentWeatherById(favoriteEntity.id)
//
//
//        Assert.assertEquals(currentWeatherEntity, result)
//
//    }
//
//}