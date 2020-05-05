package com.jeefersan.data.weatherforecast.datasources.local.db.models.currentweather

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity

/**
 * Created by JeeferSan on 4-5-20.
 */


class CurrentWeatherWithFavoriteEntity {


    @Embedded
    lateinit var favoriteEntity: FavoriteEntity

    @Embedded
    lateinit var currentWeatherEntity: CurrentWeatherEntity
}