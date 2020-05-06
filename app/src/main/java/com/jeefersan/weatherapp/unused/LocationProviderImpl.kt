package com.jeefersan.weatherapp.unused

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jeefersan.data.weatherforecast.util.shouldUpdate
import com.jeefersan.data.unused.location.datasources.LocationProvider
import com.jeefersan.domain.Coordinates
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.misc.Constants
import kotlinx.coroutines.tasks.await

/**
 * Created by JeeferSan on 24-4-20.
 */
class LocationProviderImpl(
    private val context: Context
) : LocationProvider {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var lastCoordinates: Coordinates

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)


    private var lastUpdateTime = sharedPreferences.getLong(
        Constants.Keys.LOCATION_LAST_UPDATE,
        System.currentTimeMillis()
    )


    @SuppressLint("CommitPrefEdits")
    override suspend fun getCurrentCoordinates(): Result<Coordinates> {
        try {

            if (!shouldUpdate(
                    lastUpdateTime
                ) && this::lastCoordinates.isInitialized) {
                return Result.Success(lastCoordinates)
            }

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            val location = fusedLocationProviderClient.lastLocation.await()
            Log.d("locationprovider", "and ${location.longitude + location.latitude}")

            lastCoordinates = Coordinates(location.latitude, location.longitude)
            sharedPreferences.edit().apply {
                putLong(Constants.Keys.LOCATION_LAST_UPDATE, System.currentTimeMillis())
            }.apply()

            return Result.Success(lastCoordinates)
        } catch (throwable: Throwable) {
            return Result.Failure(throwable)

        }
    }

//    private fun shouldUpdate(lastUpdateTime: Long): Boolean {
//        return (System.currentTimeMillis() - lastUpdateTime) >= THRESHOLD
//    }

//    companion object {
//        const val TRESHOLD = 3 * 10 * 60 * 1000
//    }

}