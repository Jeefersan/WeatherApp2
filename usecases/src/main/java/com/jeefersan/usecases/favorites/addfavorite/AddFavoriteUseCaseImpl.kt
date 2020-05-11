package com.jeefersan.usecases.favorites.addfavorite

import com.jeefersan.data.favorites.repositories.FavoritesRepository

import com.jeefersan.domain.Favorite
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 21-4-20.
 */
class AddFavoriteUseCaseImpl(
    private val favoritesRepository: FavoritesRepository
) :
    AddFavoriteUseCase {
    override suspend fun invoke(location: Location): Result<Unit> =
        try {

            val favorite = Favorite(
                favoriteId = 0,
                cityName = location.cityName,
                latitude = location.coordinates.lat,
                longitude = location.coordinates.long,
                lastForecastUpdate = 0,
                lastCurrentUpdate = 0
            )
            when (val result = favoritesRepository.addFavorite(favorite)) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(Unit)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }


}