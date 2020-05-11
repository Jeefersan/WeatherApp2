package com.jeefersan.usecases.favorites.getallfavorites

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

/**
 * Created by JeeferSan on 5-5-20.
 */


class GetAllFavoritesUseCaseImpl(private val favoritesRepository: FavoritesRepository) :
    GetAllFavoritesUseCase {
    override suspend fun invoke(): Result<List<Favorite>> =
        try {
            when (val result = favoritesRepository.getAllFavorites()) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(result.data)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}