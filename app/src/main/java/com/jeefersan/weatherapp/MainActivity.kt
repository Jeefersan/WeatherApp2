package com.jeefersan.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jeefersan.weatherapp.databinding.ActivityMainBinding
import com.jeefersan.weatherapp.framework.workers.WorkerActivity
import com.jeefersan.weatherapp.misc.ACTION_CODE
import com.jeefersan.weatherapp.misc.FAVORITE_ID
import com.jeefersan.weatherapp.misc.TOP_LEVEL_DESTINATIONS
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.FavoriteForecastFragmentArgs

class MainActivity : WorkerActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.extras != null) {
            if (intent.action == ACTION_CODE) {
                val id = intent.extras!!.getInt(FAVORITE_ID)
                val bundle = Bundle()
                bundle.putInt(FAVORITE_ID, id)


                val args = FavoriteForecastFragmentArgs(id)
                Log.d("MainActivity", "about to navigate")
                navController.navigate(R.id.nav_favorite_forecast, args.toBundle())
            }
        }
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
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}

// TODO: all location usecases shouldn't be usecases -> remove locationprovder from data layer