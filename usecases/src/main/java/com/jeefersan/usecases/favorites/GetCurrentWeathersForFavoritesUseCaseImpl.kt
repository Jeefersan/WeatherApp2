package com.jeefersan.usecases.favorites

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.weatherforecast.repositories.WeatherRepository
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.FavoriteCurrentWeather
import com.jeefersan.usecases.shouldUpdateCurrent
import com.jeefersan.util.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Created by JeeferSan on 3-5-20.
 */

class GetCurrentWeathersForFavoritesUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
    private val weatherRepository: WeatherRepository
) : GetCurrentWeathersForFavoritesUseCase {
    override suspend operator fun invoke(): Result<List<FavoriteCurrentWeather>> =
        try {
            val favorites = kotlin.run {
                val favoritesResult = favoritesRepository.getAllFavorites()
                if (favoritesResult is Result.Failure) return favoritesResult
                (favoritesResult as Result.Success).data
            }
            val favoriteCurrentWeatherList = coroutineScope {
                async {
                    val list = mutableListOf<FavoriteCurrentWeather>()
                    favorites.map { favorite ->
                        async {
                            val result = weatherRepository.getCurrentWeather(
                                id = favorite.favoriteId,
                                coordinates = Coordinates(favorite.latitude, favorite.longitude),
                                shouldUpdate = shouldUpdateCurrent(favorite.lastCurrentUpdate)
                            )
                            if (result is Result.Success) {
                                list.add(FavoriteCurrentWeather(favorite, result.data))
                                launch { favoritesRepository.setCurrentUpdate(favorite.favoriteId) }
                            }
                            else{
                               Result.Failure(Throwable("Failed"))
                            }
                        }
                    }.awaitAll()
                    list
                }
            }.await()
            Result.Success(favoriteCurrentWeatherList.toList())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }

}