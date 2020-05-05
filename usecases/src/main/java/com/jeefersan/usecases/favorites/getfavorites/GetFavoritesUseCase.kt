package com.jeefersan.usecases.favorites.getfavorites

import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 5-5-20.
 */
interface GetFavoritesUseCase {
    suspend operator fun invoke() : Flow<Result<List<Favorite>>>
}