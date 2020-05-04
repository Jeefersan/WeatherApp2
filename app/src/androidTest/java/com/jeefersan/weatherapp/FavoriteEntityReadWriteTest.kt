//package com.jeefersan.weatherapp
//
//import androidx.room.Room
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.jeefersan.data.favorites.datasources.local.datasources.FavoritesLocalDataSourceImpl
//import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
//import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
//import com.jeefersan.data.favorites.datasources.local.utils.mapToFavorite
//import com.jeefersan.data.favorites.repositories.FavoritesRepository
//import com.jeefersan.data.favorites.repositories.FavoritesRepositoryImpl
//import com.jeefersan.util.Result
//import com.jeefersan.weatherapp.framework.db.LocalDatabase
//import com.jeefersan.weatherappFavoriteEntity.provideFakeFavoriteEntity
//import kotlinx.coroutines.runBlocking
//import org.hamcrest.core.IsEqual
//import org.junit.After
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.koin.test.KoinTest
//import org.koin.test.inject
//
///**
// * Created by JeeferSan on 3-5-20.
// */
//
//@RunWith(AndroidJUnit4::class)
//class FavoriteEntityReadWriteTest: KoinTest {
//    private lateinit var favoritesDao: FavoritesDao
//    private lateinit var db: LocalDatabase
//    private lateinit var favoritesRepository: FavoritesRepository
//
//    val favsRepository: FavoritesRepository by inject()
//
//
////    @Before
////    fun createDb() {
////        val context = InstrumentationRegistry.getInstrumentation().context
////        db = Room.inMemoryDatabaseBuilder(
////            context,
////            LocalDatabase::class.java
////        ).build()
////        favoritesDao = db.favoriteWeatherForecastDao()
////        favoritesRepository = FavoritesRepositoryImpl(FavoritesLocalDataSourceImpl(favoritesDao))
////    }
//
////    @After
////    fun closeDb() {
////        db.close()
////    }
//
//    @Test
//    fun writeFavoriteEntityAndRead() {
//        runBlocking {
//            val favorite= provideFakeFavoriteEntity().mapToFavorite()
//
//            favoritesRepository.addFavorite(favorite)
//
//            val favoritesList = kotlin.run {
//                val result = favoritesRepository.getAllFavorites()
//                (result as Result.Success).data
//            }
//
//
//            Assert.assertThat(favorite, IsEqual.equalTo(favoritesList.first()))
//        }
//
//    }
//
//}