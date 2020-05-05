package com.jeefersan.weatherapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeefersan.data.favorites.datasources.local.db.FavoritesDao
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.dao.*
import com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather.CurrentWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.hourlyforecast.HourlyWeatherEntity
import com.jeefersan.data.weatherforecast.datasources.local.db.models.weeklyforecast.DailyWeatherEntity

/**
 * Created by JeeferSan on 24-4-20.
 */

@Database(
    entities = [FavoriteEntity::class, HourlyWeatherEntity::class, DailyWeatherEntity::class, CurrentWeatherEntity::class],
    exportSchema = false,
    version = 1
)

//  CurrentWeatherEntity::class, HourlyForecastWithFavoriteEntity::class, WeeklyForecastWithFavoriteEntity::class, HourlyWeatherEntity::class, DailyWeatherEntity::class]
abstract class LocalDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun hourlyForecastDao(): HourlyForecastDao
    abstract fun weeklyForecastDao(): WeeklyForecastDao

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