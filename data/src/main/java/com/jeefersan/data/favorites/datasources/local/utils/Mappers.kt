package com.jeefersan.data.favorites.datasources.local.utils

import com.jeefersan.data.favorites.datasources.local.models.FavoriteEntity
import com.jeefersan.domain.Favorite

/**
 * Created by JeeferSan on 3-5-20.
 */

fun Favorite.mapToLocal() : FavoriteEntity =
    FavoriteEntity(favoriteId, cityName, latitude, longitude, lastUpdateTime)

//fun FavoriteEntity.mapToDomain() : Favorite =
//    Favorite(id, cityName, latitude, longitude, lastUpdateTime)