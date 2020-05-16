package com.jeefersan.weatherapp.presentation.base

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.misc.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by JeeferSan on 12-5-20.
 */
abstract class BaseActivity : AppCompatActivity() {


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
            if (!isLocationEnabled(this)) {
                showAlertDialog()
            }
            setupUi()
        }
    }

    abstract fun setupUi()

    private fun showAlertDialog() {
       AlertDialog.Builder(this)
            .apply {
                setTitle("GPS")
                setMessage(getString(R.string.turn_on_gps))
                setPositiveButton(
                    "Settings"
                ) { _, _ -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
                setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.cancel()
                    showSnackbar(main_layout, getString(R.string.gps_disabled))
                }
            }.show()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.first() == PackageManager.PERMISSION_GRANTED) checkPermissions()
                else {
                    showSnackbar(main_layout, getString(R.string.no_location_permission))
                    setupUi()
                }
            }
        }
    }


}