package com.jeefersan.weatherapp.presentation.favoriteweatherforecast

import android.R.layout
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentFavoriteWeatherForecastBinding
import com.jeefersan.weatherapp.misc.showDailyCustomDialog
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.adapters.DailyDetailAdapter
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.adapters.HourlyDetailAdapter
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels.FavoriteForecastViewModelImpl
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


/**
 * Created by JeeferSan on 8-5-20.
 */
class FavoriteForecastFragment : BaseFragment<FragmentFavoriteWeatherForecastBinding>() {
    private val args: FavoriteForecastFragmentArgs by navArgs()

    private val viewModel: FavoriteForecastViewModelImpl by viewModel { parametersOf(args.favoriteId) }

    override val layoutId: Int = R.layout.fragment_favorite_weather_forecast

    override fun getViewModel(): BaseViewModel = viewModel

    private val hourlyCallback = (object : ViewPager2.OnPageChangeCallback() {
    })

    private val dailyCallback = (object : ViewPager2.OnPageChangeCallback() {
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupHourlyAdapter()
        setupDailyAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setupBinding() {
        getBinding().apply {
            lifecycleOwner = this@FavoriteForecastFragment
            vm = viewModel
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.cityName.observe(this, Observer { getBinding().current.cityName = it })
        viewModel.currentWeather.observe(this, Observer { getBinding().current.currentWeather = it })
        viewModel.showDialog.observe(this, Observer {

            showDailyCustomDialog(it) })

    }

    private fun setupHourlyAdapter() {
        val hourlyDetailsArray = resources.getStringArray(R.array.hourly_detail_names)

        val hourlyDetailAdapter = HourlyDetailAdapter(this, hourlyDetailsArray.size)
        getBinding().viewpagerHourly.apply {
            adapter = hourlyDetailAdapter
            registerOnPageChangeCallback(hourlyCallback)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(getBinding().tabLayout, getBinding().viewpagerHourly) { tab, position ->
            tab.text = hourlyDetailsArray[position]
        }.attach()
    }

    private fun setupDailyAdapter(){
        val dailyDetailsArray = resources.getStringArray(R.array.daily_detail_names)

        val dailyDetailAdapter = DailyDetailAdapter(this, dailyDetailsArray.size)
        getBinding().viewpagerDaily.apply {
            adapter = dailyDetailAdapter
            registerOnPageChangeCallback(dailyCallback)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(getBinding().dailyTabLayout, getBinding().viewpagerDaily) { tab, position ->
            tab.text = dailyDetailsArray[position]
        }.attach()
    }


    override fun onDestroy() {
        super.onDestroy()
        getBinding().viewpagerHourly.unregisterOnPageChangeCallback(hourlyCallback)
        getBinding().viewpagerDaily.unregisterOnPageChangeCallback(dailyCallback)
    }
}


