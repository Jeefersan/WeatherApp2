//package com.jeefersan.data.weatherforecast.datasources.local.db.models
//
//data class ForecastEntity(
//    @Embedded val weatherForecastEntity: WeatherForecastEntity,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "currentWeatherId",
//        entity = CurrentWeatherEntity::class
//    )
//    val currentWeatherEntity: CurrentWeatherEntity,
//
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "dailyWeatherId",
//        entity = DailyWeatherEntity::class
//    )
//    val dailyWeathers: List<DailyWeatherEntity>,
//
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "hourlyWeatherId",
//        entity = HourlyWeatherEntity::class
//    )
//    val hourlyWeathers: List<HourlyWeatherEntity>
//
//)