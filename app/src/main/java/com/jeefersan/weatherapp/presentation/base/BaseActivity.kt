package com.jeefersan.weatherapp.presentation.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.misc.LOCATION_REQUEST_CODE
import com.jeefersan.weatherapp.misc.isAccessFineLocationGranted
import com.jeefersan.weatherapp.misc.requestAccessPermissions
import com.jeefersan.weatherapp.misc.showSnackbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by JeeferSan on 12-5-20.
 */
abstract class BaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions()
    }

    private fun checkPermissions() {
        if (!isAccessFineLocationGranted(this)) {
            requestAccessPermissions(
                this,
                LOCATION_REQUEST_CODE
            )
        } else {
            Log.i("MainActivity", "Location permissions granted")
            setupUi()
        }
    }

    abstract fun setupUi()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.first() == PackageManager.PERMISSION_GRANTED) setupUi()
                else {
                    showSnackbar(main_layout, getString(R.string.no_location_permission))
                    setupUi()
                }
            }
        }
    }


}