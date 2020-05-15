package com.jeefersan.weatherapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentHomeBinding
import com.jeefersan.weatherapp.misc.getOpenWeatherIconRes
import com.jeefersan.weatherapp.misc.setAnimation
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.home.adapters.HourlyWeatherAdapter
import com.jeefersan.weatherapp.presentation.home.viewmodels.HomeViewModelImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by JeeferSan on 23-4-20.
 */

@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModelImpl by viewModel()

    override val layoutId: Int = R.layout.fragment_home

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            lifecycleOwner = this@HomeFragment
            vm = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    override fun initObservers() {
        super.initObservers()

        lifecycleScope.launchWhenCreated {

            viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
                getBinding().apply {
                    currentWeatherModel = it
                    ivWeather.setImageResource(getOpenWeatherIconRes(it.icon))
                }
            })

            viewModel.loadingStatus.observe(viewLifecycleOwner, Observer {

                getBinding().apply {
                    tvCity.setAnimation(requireContext(), R.anim.translate)
                    tvTemperature.setAnimation(requireContext(), R.anim.translate)
                    ivWeather.setAnimation(requireContext(), R.anim.scale)
                }

            })

            viewModel.hourlyForecast.observe(
                viewLifecycleOwner,
                Observer
                {
                    getBinding().rvHourlyWeather.adapter = HourlyWeatherAdapter(it.hourlyForecast)
                })

        }

    }

}
