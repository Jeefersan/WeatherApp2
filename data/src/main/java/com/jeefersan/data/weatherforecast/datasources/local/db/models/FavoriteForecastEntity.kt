//package com.jeefersan.data.weatherforecast.datasources.local.db.models
//
//import androidx.room.Embedded
//import androidx.room.Relation
//
///**
// * Created by JeeferSan on 4-5-20.
// */
//
//data class FavoriteForecastEntity(
//    @Embedded val favoriteEntity: FavoriteEntity,
//    @Relation(
//        parentColumn = "favoriteId",
//        entityColumn = "currentWeatherId",
//        entity = CurrentWeatherEntity::class
//    )
//    val currentWeatherEntity: CurrentWeatherEntity,
//
//    @Relation(
//        parentColumn = "favoriteId",
//        entityColumn = "hourlyWeatherId",
//        entity = HourlyWeatherEntity::class
//    )
//    val hourlyForecast: List<HourlyWeatherEntity>,
//
//    @Relation(
//        parentColumn = "favoriteId",
//        entityColumn = "dailyWeatherId",
//        entity = DailyWeatherEntity::class
//    )
//    val weeklyForecast: List<DailyWeatherEntity>
//)