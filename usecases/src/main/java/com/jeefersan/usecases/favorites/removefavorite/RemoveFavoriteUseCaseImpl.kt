package com.jeefersan.usecases.favorites.removefavorite

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.weatherforecast.repositories.WeatherRepository
import com.jeefersan.util.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/**
 * Created by JeeferSan on 21-4-20.
 */

class RemoveFavoriteUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
    private val weatherRepository: WeatherRepository
) :
    RemoveFavoriteUseCase {
    override suspend fun invoke(id: Int): Result<Unit> {
        return try {
            val results = coroutineScope {
                awaitAll(
                    async { favoritesRepository.removeFavoriteById(id) },
                    async { weatherRepository.removeAllById(id) }
                )
            }
            results
                .find { it is Result.Failure }
                ?: Result.Success(Unit)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

    }
}