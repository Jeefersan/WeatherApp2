package com.jeefersan.weatherapp.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.WeatherForecastDao
import com.jeefersan.data.weatherforecast.datasources.local.db.models.*

/**
 * Created by JeeferSan on 24-4-20.
 */

@Database(
    entities = [FavoriteEntity::class, WeatherForecastEntity::class, ForecastEntity::class],
    exportSchema = false,
    version = 1
)

//  CurrentWeatherEntity::class, HourlyForecastEntity::class, WeeklyForecastEntity::class, HourlyWeatherEntity::class, DailyWeatherEntity::class]
abstract class LocalDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    abstract fun weatherForecastDao(): WeatherForecastDao
}

//private lateinit var INSTANCE: LocalDatabase
//
//fun getDatabase(context: Context): LocalDatabase {
//    synchronized(LocalDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                LocalDatabase::class.java,
//                "weather-db"
//            ).build()
//        }
//    }
//    return INSTANCE
//}