package com.jeefersan.weatherapp.framework.di

import androidx.room.Room
import com.jeefersan.data.unused.currentweather.datasources.remote.WeatherRemoteDataSource
import com.jeefersan.data.unused.currentweather.datasources.remote.WeatherRemoteDataSourceImpl
import com.jeefersan.data.unused.currentweather.repositories.WeatherRepository
import com.jeefersan.data.unused.currentweather.repositories.WeatherRepositoryImpl
import com.jeefersan.data.flowlocation.FlowLocationProvider
import com.jeefersan.data.unused.location.datasources.LocationProvider
import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSource
import com.jeefersan.data.weatherforecast.datasources.local.WeatherForecastLocalDataSourceImpl
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherForecastRemoteDataSource
import com.jeefersan.data.weatherforecast.datasources.remote.WeatherForecastRemoteDataSourceImpl
import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepository
import com.jeefersan.data.weatherforecast.repositories.WeatherForecastRepositoryImpl
import com.jeefersan.usecases.currentweather.getweatherforcurrentlocation.GetWeatherForCurrentLocationUseCaseImpl
import com.jeefersan.usecases.currentweather.getweatherforcurrentlocation.GetWeatherForCurrentLocationUseCase
import com.jeefersan.usecases.forecast.getweatherforecastfromlocation.GetWeatherForecastFromLocationUseCase
import com.jeefersan.usecases.forecast.getweatherforecastfromlocation.GetWeatherForecastFromLocationUsecaseImpl
import com.jeefersan.usecases.location.GetCurrentLocationUseCase
import com.jeefersan.usecases.location.GetCurrentLocationUseCaseImpl
import com.jeefersan.weatherapp.framework.db.LocalDatabase
import com.jeefersan.weatherapp.framework.location.FlowLocationProviderImpl
import com.jeefersan.weatherapp.framework.location.LocationProviderImpl
import com.jeefersan.weatherapp.framework.network.api.provideApiService
import com.jeefersan.weatherapp.framework.network.api.provideInterceptor
import com.jeefersan.weatherapp.framework.network.api.provideOkHttpClient
import com.jeefersan.weatherapp.framework.network.api.provideRetrofit
import com.jeefersan.weatherapp.presentation.favorites.FavoritesViewModelImpl
import com.jeefersan.weatherapp.models.WeeklyForecastModel
import com.jeefersan.weatherapp.presentation.home.viewmodels.HomeViewModelImpl
import com.jeefersan.weatherapp.presentation.settings.SettingsViewModelImpl
import com.jeefersan.weatherapp.presentation.weeklyforecast.WeeklyForecastViewModelImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
val flowModule = module {
    single<FlowLocationProvider> { FlowLocationProviderImpl(androidContext()) }
}


@ExperimentalCoroutinesApi
val useCaseModule = module {
    factory<GetWeatherForCurrentLocationUseCase> {
        GetWeatherForCurrentLocationUseCaseImpl(
            get(),
            get()
        )
    }
    factory<GetWeatherForecastFromLocationUseCase> { GetWeatherForecastFromLocationUsecaseImpl(get()) }
    factory<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
}

val weatherModule = module {
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
    single<WeatherForecastRepository> { WeatherForecastRepositoryImpl(get(), get()) }
    single<WeatherForecastRemoteDataSource> {
        WeatherForecastRemoteDataSourceImpl(
            get()
        )
    }
    single<LocationProvider> { LocationProviderImpl(androidContext()) }
    single <WeatherForecastLocalDataSource>{ WeatherForecastLocalDataSourceImpl(get()) }
}

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel {
        HomeViewModelImpl(
            get(),
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
    viewModel { FavoritesViewModelImpl() }

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
    single { get<LocalDatabase>().weatherForecastDao() }
}

val sharedPreferencesModule = module {
    single {
        androidApplication().getSharedPreferences(
            "SHARED_PREFS",
            android.content.Context.MODE_PRIVATE
        )
    }
}

