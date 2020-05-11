package com.jeefersan.weatherapp.framework.location

import com.jeefersan.data.location.datasources.LocationProvider
import com.jeefersan.data.location.datasources.LocationRemoteDataSource
import com.jeefersan.data.location.repositories.LocationRepository
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow


/**
 * Created by JeeferSan on 25-4-20.
 */

class LocationRepositoryImpl(
    private val locationProvider: LocationProvider,
    private val locationRemoteDataSource: LocationRemoteDataSource
) : LocationRepository {


    override fun getCurrentLocation(): Flow<Result<Location>> =
        locationProvider.getCurrentLocation()

    override suspend fun searchLocationsWithQuery(query: String) =
        locationRemoteDataSource.queryLocations(query)

    override suspend fun retrieveSearchResult(): Flow<Result<List<Location>>> =
        locationRemoteDataSource.retrieveLocations()
}


