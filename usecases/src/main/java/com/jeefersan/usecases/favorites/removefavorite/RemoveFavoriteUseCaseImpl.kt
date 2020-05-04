package com.jeefersan.usecases.favorites.removefavorite

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.domain.Favorite
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */

class RemoveFavoriteUseCaseImpl(private val favoritesRepository: FavoritesRepository) :
    RemoveFavoriteUseCase {
    override suspend fun invoke(favorite: Favorite): Result<Unit> = try {
        favoritesRepository.removeFavoriteById(favorite.favoriteId)
    } catch (throwable: Throwable) {
        Result.Failure(throwable)
    }
}