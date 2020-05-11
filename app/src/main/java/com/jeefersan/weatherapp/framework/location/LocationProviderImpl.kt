package com.jeefersan.weatherapp.framework.location

import android.content.Context
import android.location.Geocoder
import android.os.Looper
import com.google.android.gms.location.*
import com.jeefersan.data.location.datasources.LocationProvider
import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.isDistanceGreaterThan5Km
import com.jeefersan.weatherapp.misc.isLocationEnabled
import com.jeefersan.weatherapp.misc.mapToCoordinates
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by JeeferSan on 28-4-20.
 */

@ExperimentalCoroutinesApi
class LocationProviderImpl(
    context: Context
) : LocationProvider {

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val geocoder: Geocoder = Geocoder(context, Locale.getDefault())


    private fun getLocationFromCoordinates(coordinates: Coordinates): Location =
        geocoder.getFromLocation(coordinates.lat, coordinates.long, 1)
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


    override fun getCurrentLocation() = channelFlow<Result<Location>> {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                result?.lastLocation?.let {
                    val location =
                    getLocationFromCoordinates(it.mapToCoordinates())
                    offer(Result.Success(location))
                }
            }
        }

        fusedLocationProviderClient.lastLocation.await<android.location.Location?>()
            ?.let { send(Result.Success(getLocationFromCoordinates(it.mapToCoordinates()))) }
        fusedLocationProviderClient.requestLocationUpdates(
            getLocationRequest(), locationCallback, null
        ).await()
        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }

    }

//    private fun hasLocationChanged(currentLocation: android.location.Location): Boolean {
//        if (mostRecentLocation != null) {
//            return mostRecentLocation!!.isDistanceGreaterThan5Km(currentLocation)
//        }
//        return true
//    }


}
