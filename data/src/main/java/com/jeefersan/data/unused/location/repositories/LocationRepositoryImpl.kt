package com.jeefersan.data.unused.location.repositories

import com.jeefersan.data.unused.location.datasources.LocationProvider
import com.jeefersan.domain.Coordinates
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 25-4-20.
 */

class LocationRepositoryImpl(private val locationProvider: LocationProvider) :
    LocationRepository {


    override suspend fun getCurrentLocation(): Result<Coordinates> {
        TODO("Not yet implemented")
    }

}