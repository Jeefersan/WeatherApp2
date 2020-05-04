//package com.jeefersan.usecases.currentweather.getweatherfromsavedlocations
//
//import com.jeefersan.data.favorites.repositories.FavoritesRepository
//import com.jeefersan.data.unused.currentweather.repositories.WeatherRepository
//import com.jeefersan.domain.CurrentWeather
//import com.jeefersan.util.Result
//
///**
// * Created by JeeferSan on 21-4-20.
// */
//class GetWeatherForSavedLocationsUseCaseImpl(
//    private val weatherRepository: WeatherRepository,
//    private val favoritesRepository: FavoritesRepository
//) : GetWeatherForSavedLocationsUseCase {
//    override suspend fun invoke(): Result<List<CurrentWeather>> {
//        try {
//            val savedLocations = kotlin.run {
//                val savedLocationsResult = favoritesRepository.getSavedLocations()
//                if (savedLocationsResult is Result.Failure) return savedLocationsResult
//                (savedLocationsResult as Result.Success).data
//            }
//            return when (val result =
//                weatherRepository.getMultipleCurrentWeathers(savedLocations)) {
//                is Result.Success -> Result.Success(result.data)
//                else -> result
//            }
//        } catch (throwable: Throwable) {
//            return Result.Failure(throwable)
//        }
//    }
//}