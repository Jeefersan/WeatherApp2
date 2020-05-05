package com.jeefersan.usecases.favorites.getfavorites

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Created by JeeferSan on 5-5-20.
 */

@ExperimentalCoroutinesApi
class GetFavoritesUseCaseImpl(private val favoritesRepository: FavoritesRepository) :
    GetFavoritesUseCase {
    override suspend fun invoke(): Flow<Result<List<Favorite>>> =
        favoritesRepository.getAllFavorites()
            .map { result ->
                if (result is Result.Success) {
                    Result.Success(result.data)
                } else {
                    result
                }
            }
            .catch { t -> emit(Result.Failure(t)) }
            .flowOn(Dispatchers.IO)
}