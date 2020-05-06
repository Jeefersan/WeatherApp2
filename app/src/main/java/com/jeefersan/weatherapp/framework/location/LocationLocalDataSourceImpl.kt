package com.jeefersan.weatherapp.framework.location

import android.content.Context
import android.location.Geocoder
import android.os.Looper
import com.google.android.gms.location.*
import com.jeefersan.data.location.datasources.LocationLocalDataSource
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.isDistanceGreaterThan5Km
import com.jeefersan.weatherapp.misc.mapToCoordinates
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by JeeferSan on 28-4-20.
 */

@ExperimentalCoroutinesApi
class LocationLocalDataSourceImpl(
    context: Context
) : LocationLocalDataSource {
    private var mostRecentLocation: android.location.Location? = null

    private lateinit var locationCallback: LocationCallback

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val geocoder: Geocoder = Geocoder(context, Locale.getDefault())


    private val channel = ConflatedBroadcastChannel<Result<Location>>()

    init {
        startLocationUpdates()
    }

    private lateinit var locationRequest: LocationRequest


    private fun getLocationFromCoordinates(coordinates: Coordinates) {
        val location = geocoder.getFromLocation(coordinates.lat, coordinates.long, 1)
            .filter { it.locality != null }
            .map {
                Location(
                    cityId = null,
                    cityName = it.locality,
                    coordinates = coordinates,
                    country = null
                )
            }
            .first()
        if (!channel.isClosedForSend) {
            channel.offer(Result.Success(location))
        }

    }

    private fun startLocationUpdates() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val newLocation = locationResult.lastLocation
                if (hasLocationChanged(newLocation)) {
                    mostRecentLocation = newLocation
                    getLocationFromCoordinates(newLocation.mapToCoordinates())
                }
            }
        }

        locationRequest = LocationRequest().apply {
            interval = TimeUnit.MINUTES.toMinutes(15)
            fastestInterval = TimeUnit.MINUTES.toMinutes(10)
            maxWaitTime = TimeUnit.MINUTES.toMinutes(3)
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }


    @FlowPreview
    override fun getCurrentLocation(): Flow<Result<Location>> {
        return channel.asFlow()
    }

    private fun hasLocationChanged(currentLocation: android.location.Location): Boolean {
        if (mostRecentLocation != null) {
            return mostRecentLocation!!.isDistanceGreaterThan5Km(currentLocation)
        }
        return true
    }


}