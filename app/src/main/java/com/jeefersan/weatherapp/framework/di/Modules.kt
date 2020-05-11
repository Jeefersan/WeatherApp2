package com.jeefersan.weatherapp.framework.di

import androidx.room.Room
import com.jeefersan.data.favorites.datasources.local.FavoritesLocalDataSource
import com.jeefersan.data.favorites.datasources.local.FavoritesLocalDataSourceImpl
import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.data.favorites.repositories.FavoritesRepositoryImpl
import com.jeefersan.data.location.datasources.LocationProvider
import com.jeefersan.data.location.datasources.LocationRemoteDataSource
import com.jeefersan.data.location.repositories.LocationRepository
import com.jeefersan.weatherapp.framework.location.LocationRepositoryImpl
import com.jeefersan.data.weatherforecast.datasources.local.WeatherLocalDataSource
import com.jeefersan.data.weatherforecast.datasources.local.WeatherLocalDataSourceImpl
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherRemoteDataSource
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherRemoteDataSourceImpl
import com.jeefersan.data.weatherforecast.repositories.WeatherRepository
import com.jeefersan.data.weatherforecast.repositories.WeatherRepositoryImpl
import com.jeefersan.usecases.favorites.GetCurrentWeathersForFavorites
import com.jeefersan.usecases.favorites.GetCurrentWeathersForFavoritesImpl
import com.jeefersan.usecases.favorites.addfavorite.AddFavoriteUseCase
import com.jeefersan.usecases.favorites.addfavorite.AddFavoriteUseCaseImpl
import com.jeefersan.usecases.favorites.getallfavorites.GetAllFavoritesUseCase
import com.jeefersan.usecases.favorites.getallfavorites.GetAllFavoritesUseCaseImpl
import com.jeefersan.usecases.favorites.removefavorite.RemoveFavoriteUseCase
import com.jeefersan.usecases.favorites.removefavorite.RemoveFavoriteUseCaseImpl
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastUseCase
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastUseCaseUsecaseImpl
import com.jeefersan.usecases.location.getcurrentlocation.GetCurrentLocationUseCase
import com.jeefersan.usecases.location.getcurrentlocation.GetCurrentLocationUseCaseImpl
import com.jeefersan.usecases.location.retrievelocations.RetrieveLocationsUseCase
import com.jeefersan.usecases.location.retrievelocations.RetrieveLocationsUseCaseImpl
import com.jeefersan.usecases.location.searchlocations.SearchLocationsUseCase
import com.jeefersan.usecases.location.searchlocations.SearchLocationsUseCaseImpl
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastForFavoriteUseCase
import com.jeefersan.usecases.weatherforecast.GetCompleteWeatherForecastForFavoriteUseCaseImpl
import com.jeefersan.weatherapp.framework.db.LocalDatabase
import com.jeefersan.weatherapp.framework.location.LocationProviderImpl
import com.jeefersan.weatherapp.framework.location.LocationRemoteDataSourceImpl
import com.jeefersan.weatherapp.framework.network.api.provideApiService
import com.jeefersan.weatherapp.framework.network.api.provideInterceptor
import com.jeefersan.weatherapp.framework.network.api.provideOkHttpClient
import com.jeefersan.weatherapp.framework.network.api.provideRetrofit
import com.jeefersan.weatherapp.models.WeeklyForecastModel
import com.jeefersan.weatherapp.presentation.favorites.viewmodels.FavoritesViewModelImpl
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels.FavoriteForecastViewModelImpl
import com.jeefersan.weatherapp.presentation.home.viewmodels.HomeViewModelImpl
import com.jeefersan.weatherapp.presentation.search.viewmodels.SearchViewModelImpl
import com.jeefersan.weatherapp.presentation.settings.SettingsViewModelImpl
import com.jeefersan.weatherapp.presentation.weeklyforecast.viewmodels.WeeklyForecastViewModelImpl
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
    factory<AddFavoriteUseCase> { AddFavoriteUseCaseImpl(get()) }
    factory<GetCompleteWeatherForecastUseCase> {
        GetCompleteWeatherForecastUseCaseUsecaseImpl(
            get()
        )
    }
    factory<GetCurrentLocationUseCase> {
        GetCurrentLocationUseCaseImpl(
            get()
        )
    }
    factory<GetCurrentWeathersForFavorites> { GetCurrentWeathersForFavoritesImpl(get(), get()) }
    factory<GetAllFavoritesUseCase> { GetAllFavoritesUseCaseImpl(get()) }
    factory<SearchLocationsUseCase> {
        SearchLocationsUseCaseImpl(
            get()
        )
    }
    factory<RetrieveLocationsUseCase> {
        RetrieveLocationsUseCaseImpl(
            get()
        )
    }
    factory<RemoveFavoriteUseCase> { RemoveFavoriteUseCaseImpl(get(), get()) }
    factory<GetCompleteWeatherForecastForFavoriteUseCase> { GetCompleteWeatherForecastForFavoriteUseCaseImpl(get(), get()) }
}

val weatherModule = module {
//    single<CurrentWeatherRemoteDataSource> { CurrentWeatherRemoteDataSourceImpl(get()) }
    single<WeatherRepository>{
        WeatherRepositoryImpl(
            get(),
            get()
        )
    }
    single<WeatherRemoteDataSource> {
        WeatherRemoteDataSourceImpl(
            get()
        )
    }
    single<WeatherLocalDataSource> {
        WeatherLocalDataSourceImpl(
            get(),
            get(),
            get()
        )
    }
}

@FlowPreview
@ExperimentalCoroutinesApi
val locationModule = module {
    single<LocationRepository> {
        LocationRepositoryImpl(
            get(),
            get()
        )
    }
    single<LocationProvider> { LocationProviderImpl(androidContext()) }
    single<LocationRemoteDataSource> { LocationRemoteDataSourceImpl(get()) }
    single { providePlacesClient() }
}

val favoritesModule = module {
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<FavoritesLocalDataSource> {
        FavoritesLocalDataSourceImpl(
            get()
        )
    }
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
    viewModel {
        FavoritesViewModelImpl(
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        SearchViewModelImpl(
            get(),
            get()
        )
    }
    viewModel {(id: Int) -> FavoriteForecastViewModelImpl(get(), id) }
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

