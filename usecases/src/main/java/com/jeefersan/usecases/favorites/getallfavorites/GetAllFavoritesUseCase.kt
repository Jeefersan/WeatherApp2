package com.jeefersan.usecases.favorites.getallfavorites

import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 5-5-20.
 */
interface GetAllFavoritesUseCase {
    suspend operator fun invoke() : Result<List<Favorite>>
}