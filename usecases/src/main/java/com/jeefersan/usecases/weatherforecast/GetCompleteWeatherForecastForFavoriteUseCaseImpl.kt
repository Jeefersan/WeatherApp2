package com.jeefersan.usecases.weatherforecast

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.weatherforecast.repositories.WeatherRepository
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.FavoriteWeatherForecast
import com.jeefersan.usecases.shouldUpdateForecast
import com.jeefersan.util.Result

class GetCompleteWeatherForecastForFavoriteUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
    private val weatherRepository: WeatherRepository
) :
    GetCompleteWeatherForecastForFavoriteUseCase {

    override suspend fun invoke(id: Int): Result<FavoriteWeatherForecast> =
        try {
            val favorite = kotlin.run {
                val favoritesResult = favoritesRepository.getFavoriteById(id)
                if (favoritesResult is Result.Failure) return favoritesResult
                (favoritesResult as Result.Success).data
            }

            val shouldUpdate = shouldUpdateForecast(favorite.lastForecastUpdate)

            when (val result = weatherRepository.getCompleteWeatherForecast(
                favorite.favoriteId,
                Coordinates(favorite.latitude, favorite.longitude),
                shouldUpdate
            )) {
                is Result.Failure -> result
                is Result.Success -> {
                    favoritesRepository.setForecastUpdate(favorite.favoriteId)
                    val weatherForecast = result.data
                    Result.Success(
                        FavoriteWeatherForecast(
                            favorite,
                            weatherForecast.currentWeather,
                            weatherForecast.hourlyForecast,
                            weatherForecast.weeklyForecast
                        )
                    )
                }
            }

        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}