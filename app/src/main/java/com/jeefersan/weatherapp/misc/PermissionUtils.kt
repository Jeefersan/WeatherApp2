package com.jeefersan.weatherapp.misc

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by JeeferSan on 27-4-20.
 */


fun requestAccessPermissions(activity: Activity, requestId: Int) {
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ),
        requestId
    )
}

@RequiresApi(Build.VERSION_CODES.P)
fun isForegroundPermissionApproved(context: Context): Boolean {
    return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.FOREGROUND_SERVICE
    )
}

@RequiresApi(Build.VERSION_CODES.P)
fun requestForegroundPermission(activity: Activity, requestId: Int) =
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(Manifest.permission.FOREGROUND_SERVICE),
        requestId
    )


fun isAccessFineLocationGranted(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}


fun isLocationEnabled(context: Context): Boolean {
    val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

