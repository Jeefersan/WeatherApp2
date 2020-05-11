package com.jeefersan.weatherapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.jeefersan.weatherapp.databinding.ActivityMainBinding
import com.jeefersan.weatherapp.misc.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        checkPermissions()

    }

    private fun setupUi() {
        setupBinding()
        setupNavcontroller()
        setupBottomNav()
        setupAppbar()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupNavcontroller() {
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun setupAppbar() {
        appBarConfiguration = AppBarConfiguration.Builder(TOP_LEVEL_DESTINATIONS)
            .build()

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun checkPermissions() {
        if (!isAccessFineLocationGranted(this)) {
            requestAccessCoarseLocationPermission(
                this,
                LOCATION_REQUEST_CODE
            )
        } else {
            Log.d("MainActivity", "Location permissions granted")
            setupUi()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            setupUi()
            Log.d("MainActivity", "Location permissions granted")
        } else {
            Snackbar.make(
                main_layout,
                getString(R.string.no_location_permission),
                Snackbar.LENGTH_SHORT
            )
            setupUi()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}

// TODO: all location usecases shouldn't be usecases