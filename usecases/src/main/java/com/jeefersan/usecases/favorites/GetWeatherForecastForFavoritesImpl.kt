package com.jeefersan.usecases.favorites

import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.shouldUpdate
import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSource
import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.FavoriteForecast
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 3-5-20.
 */
class GetWeatherForecastForFavoritesImpl(
    private val favoritesRepository: FavoritesRepository,
    private val weatherForecastRepository: WeatherForecastRepository
) : GetWeatherForecastForFavorites {
    override suspend fun invoke(): Result<List<FavoriteForecast>> =
        try {
            val favoriteEntites = kotlin.run {
                val result = favoritesRepository.getAllFavorites()
                if (result is Result.Failure) return result
                (result as Result.Success).data
            }

            val favoriteForecastList = mutableListOf<FavoriteForecast>()

            favoriteEntites.forEach { favorite ->
                if (!shouldUpdate(favorite.lastUpdateTime)) {
                    when (val result =
                        weatherForecastRepository.getWeatherForecastByIdFromLocal(favorite.favoriteId)) {
                        is Result.Failure -> return@forEach
                        is Result.Success -> favoriteForecastList.add(
                            FavoriteForecast(
                                favorite.cityName,
                                result.data
                            )
                        )
                    }
                } else {
                    when (val result = weatherForecastRepository.getWeatherForecastFromRemote(
                        Location(
                            null,
                            null,
                            Coordinates(favorite.latitude, favorite.longitude)
                        )
                    )) {
                        is Result.Failure -> return@forEach
                        is Result.Success -> {
                            favoriteForecastList.add(
                                FavoriteForecast(
                                    favorite.cityName,
                                    result.data
                                )
                            )
                            weatherForecastRepository.insertOrUpdate(result.data)
                        }
                    }
                }
            }
            Result.Success(favoriteForecastList)
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
}
