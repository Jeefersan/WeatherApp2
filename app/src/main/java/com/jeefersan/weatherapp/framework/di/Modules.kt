package com.jeefersan.weatherapp.framework.di

import androidx.room.Room
import com.jeefersan.data.favorites.datasources.local.datasources.FavoritesLocalDataSource
import com.jeefersan.data.favorites.datasources.local.datasources.FavoritesLocalDataSourceImpl
import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.favorites.repositories.FavoritesRepositoryImpl
import com.jeefersan.data.location.LocationProvider
import com.jeefersan.data.location.LocationSearcher
import com.jeefersan.data.unused.currentweather.datasources.remote.CurrentWeatherRemoteDataSource
import com.jeefersan.data.unused.currentweather.datasources.remote.CurrentWeatherRemoteDataSourceImpl
import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSource
import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSourceImpl
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherForecastRemoteDataSource
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherForecastRemoteDataSourceImpl
import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepositoryImpl
import com.jeefersan.usecases.favorites.GetWeatherForecastForFavorites
import com.jeefersan.usecases.favorites.GetWeatherForecastForFavoritesImpl
import com.jeefersan.usecases.favorites.getfavorites.GetFavoritesUseCase
import com.jeefersan.usecases.favorites.getfavorites.GetFavoritesUseCaseImpl
import com.jeefersan.usecases.forecast.getweatherforecastfromlocation.GetWeatherForecastFromLocationUseCase
import com.jeefersan.usecases.forecast.getweatherforecastfromlocation.GetWeatherForecastFromLocationUsecaseImpl
import com.jeefersan.usecases.location.GetCurrentLocationUseCase
import com.jeefersan.usecases.location.GetCurrentLocationUseCaseImpl
import com.jeefersan.usecases.location.SearchLocationsUseCase
import com.jeefersan.usecases.location.SearchLocationsUseCaseImpl
import com.jeefersan.weatherapp.framework.db.LocalDatabase
import com.jeefersan.weatherapp.framework.location.LocationProviderImpl
import com.jeefersan.weatherapp.framework.location.LocationSearcherImpl
import com.jeefersan.weatherapp.framework.network.api.provideApiService
import com.jeefersan.weatherapp.framework.network.api.provideInterceptor
import com.jeefersan.weatherapp.framework.network.api.provideOkHttpClient
import com.jeefersan.weatherapp.framework.network.api.provideRetrofit
import com.jeefersan.weatherapp.models.WeeklyForecastModel
import com.jeefersan.weatherapp.presentation.favorites.FavoritesViewModelImpl
import com.jeefersan.weatherapp.presentation.home.viewmodels.HomeViewModelImpl
import com.jeefersan.weatherapp.presentation.search.SearchViewModelImpl
import com.jeefersan.weatherapp.presentation.settings.SettingsViewModelImpl
import com.jeefersan.weatherapp.presentation.weeklyforecast.WeeklyForecastViewModelImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by JeeferSan on 23-4-20.
 */


val applicationModule = module {

}

val networkModule = module {
    single {
        provideApiService(
            provideRetrofit(
                provideOkHttpClient(
                    provideInterceptor()
                )
            )
        )
    }
}


@ExperimentalCoroutinesApi
val useCaseModule = module {
//    factory<GetWeatherForCurrentLocationUseCase> {
//        GetWeatherForCurrentLocationUseCaseImpl(
//            get(),
//            get()
//        )
//    }
    factory<GetWeatherForecastFromLocationUseCase> { GetWeatherForecastFromLocationUsecaseImpl(get()) }
    factory<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
    factory<GetWeatherForecastForFavorites> { GetWeatherForecastForFavoritesImpl(get(), get()) }
    factory<GetFavoritesUseCase> { GetFavoritesUseCaseImpl(get()) }
    factory<SearchLocationsUseCase> { SearchLocationsUseCaseImpl(get()) }
}

val weatherModule = module {
    single<CurrentWeatherRemoteDataSource> { CurrentWeatherRemoteDataSourceImpl(get()) }
//    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
    single<WeatherForecastRepository> { WeatherForecastRepositoryImpl(get(), get()) }
    single<WeatherForecastRemoteDataSource> {
        WeatherForecastRemoteDataSourceImpl(
            get()
        )
    }
//    single<LocationProvider> { LocationProviderImpl(androidContext()) }
    single<WeatherForecastLocalDataSource> {
        WeatherForecastLocalDataSourceImpl(
            get(),
            get(),
            get()
        )
    }
}

@FlowPreview
@ExperimentalCoroutinesApi
val locationModule = module {
    single<LocationProvider> { LocationProviderImpl(androidContext()) }
    single<LocationSearcher> { LocationSearcherImpl(get()) }
    single { providePlacesClient() }
}

val favoritesModule = module {
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<FavoritesLocalDataSource> { FavoritesLocalDataSourceImpl(get()) }
}

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel {
        HomeViewModelImpl(
            get(),
            get()
        )
    }
    viewModel { (forecast: WeeklyForecastModel, locationName: String) ->
        WeeklyForecastViewModelImpl(
            forecast, locationName
        )
    }
    viewModel { SettingsViewModelImpl() }
    viewModel { FavoritesViewModelImpl(get(), get()) }
    viewModel { SearchViewModelImpl(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            LocalDatabase::class.java,
            "weather-db"
        ).build()
    }
    single { get<LocalDatabase>().favoritesDao() }
    single { get<LocalDatabase>().currentWeatherDao() }
    single { get<LocalDatabase>().hourlyForecastDao() }
    single { get<LocalDatabase>().weeklyForecastDao() }
}

val sharedPreferencesModule = module {
    single {
        androidApplication().getSharedPreferences(
            "SHARED_PREFS",
            android.content.Context.MODE_PRIVATE
        )
    }
}

