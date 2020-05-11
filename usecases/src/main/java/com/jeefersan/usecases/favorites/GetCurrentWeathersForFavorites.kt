package com.jeefersan.usecases.favorites

import com.jeefersan.domain.Favorite
import com.jeefersan.domain.FavoriteCurrentWeather
import com.jeefersan.domain.FavoriteForecast
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 3-5-20.
 */
interface GetCurrentWeathersForFavorites {

    suspend operator fun invoke(): Result<List<FavoriteCurrentWeather>>

}
