package com.jeefersan.usecases.favorites

import com.jeefersan.domain.FavoriteCurrentWeather
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 3-5-20.
 */
interface GetCurrentWeathersForFavoritesUseCase {

    suspend operator fun invoke(): Result<List<FavoriteCurrentWeather>>

}
