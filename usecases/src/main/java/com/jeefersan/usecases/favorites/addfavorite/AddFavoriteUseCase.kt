package com.jeefersan.usecases.favorites.addfavorite

import com.jeefersan.domain.Favorite
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */

interface AddFavoriteUseCase {
    suspend operator fun invoke(location: Location): Result<Unit>
}