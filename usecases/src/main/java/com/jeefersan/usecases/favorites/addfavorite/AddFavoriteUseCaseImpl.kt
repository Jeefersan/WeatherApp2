package com.jeefersan.usecases.favorites.addfavorite

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.domain.Favorite
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */
class AddFavoriteUseCaseImpl(private val favoritesRepository: FavoritesRepository) :
    AddFavoriteUseCase {
    override suspend fun invoke(favorite: Favorite): Result<Unit> =
        try {
            favoritesRepository.addFavorite(favorite)
            Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}