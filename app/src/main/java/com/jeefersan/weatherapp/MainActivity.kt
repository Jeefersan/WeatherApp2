package com.jeefersan.weatherapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jeefersan.weatherapp.databinding.ActivityMainBinding
import com.jeefersan.weatherapp.misc.TOP_LEVEL_DESTINATIONS
import com.jeefersan.weatherapp.presentation.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)

    }

    override fun setupUi() {
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


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}

// TODO: all location usecases shouldn't be usecases -> remove locationprovder from data layer