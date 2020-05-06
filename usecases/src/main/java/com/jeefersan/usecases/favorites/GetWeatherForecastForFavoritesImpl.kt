package com.jeefersan.usecases.favorites

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.weatherforecast.util.shouldUpdate
import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.domain.*
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 3-5-20.
 */
class GetWeatherForecastForFavoritesImpl(
    private val favoritesRepository: FavoritesRepository,
    private val weatherForecastRepository: WeatherForecastRepository
) : GetWeatherForecastForFavorites {
    override suspend fun invoke(favorites: List<Favorite>): Result<List<FavoriteForecast>> =
        try {
//            val favoriteEntites = kotlin.run {
//                val result = favoritesRepository.getAllFavorites()
//                if (result is Result.Failure) return result
//                (result as Result.Success).data
//            }

            val favoriteForecastList = mutableListOf<FavoriteForecast>()

            favorites.forEach { favorite ->
                if (!shouldUpdate(
                        favorite.lastUpdateTime
                    )
                ) {
                    when (val result =
                        weatherForecastRepository.getWeatherForecastByIdFromLocal(favorite.favoriteId)) {
                        is Result.Failure -> return@forEach
                        is Result.Success -> favoriteForecastList.add(
                            FavoriteForecast(
                                favorite,
                                result.data
                            )
                        )
                    }
                } else {
                    when (val result = weatherForecastRepository.getWeatherForecastFromRemote(
                        Coordinates(favorite.latitude, favorite.longitude)

                    )) {
                        is Result.Failure -> return@forEach
                        is Result.Success -> {
                            favoriteForecastList.add(
                                FavoriteForecast(
                                    favorite,
                                    result.data
                                )
                            )
                            weatherForecastRepository.insertOrUpdate(
                                WeatherForecast(
                                    id = favorite.favoriteId,
                                    currentWeather = result.data.currentWeather,
                                    hourlyForecast = result.data.hourlyForecast,
                                    weeklyForecast = result.data.weeklyForecast
                                ))
                        }
                    }
                }
            }
            Result.Success(favoriteForecastList)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}
