package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.detail.HourlyWeatherDetailFragment

/**
 * Created by JeeferSan on 11-5-20.
 */

class HourlyDetailAdapter(fragment: Fragment, private val itemsCount: Int) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = itemsCount


    override fun createFragment(position: Int): Fragment =
        HourlyWeatherDetailFragment.getInstance(position)

}