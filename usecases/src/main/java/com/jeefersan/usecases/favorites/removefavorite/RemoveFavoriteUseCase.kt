package com.jeefersan.usecases.favorites.removefavorite

import com.jeefersan.domain.Favorite
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */
interface RemoveFavoriteUseCase {
    suspend operator fun invoke(id: Int): Result<Unit>
}