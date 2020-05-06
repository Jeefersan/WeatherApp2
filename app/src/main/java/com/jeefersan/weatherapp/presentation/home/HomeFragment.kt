package com.jeefersan.weatherapp.presentation.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentHomeBinding
import com.jeefersan.weatherapp.misc.*
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.home.adapters.HourlyWeatherAdapter
import com.jeefersan.weatherapp.presentation.home.viewmodels.HomeViewModelImpl
import kotlinx.android.synthetic.main.fragment_home.*
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkPermissions()
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun initObservers() {
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            getBinding().apply {
                currentWeatherModel = it
                ivWeather.setImageResource(getOpenWeatherIconRes(it.icon))
            }
        })

        viewModel.loadingStatus.observe(viewLifecycleOwner, Observer {
            getBinding().apply {
                tvCity.setAnimation(requireContext(), R.anim.anim_translate)
                tvTemperature.setAnimation(requireContext(), R.anim.anim_translate)
                ivWeather.setAnimation(requireContext(), R.anim.anim_scale)
            }
        })

        viewModel.hourlyForecast.observe(
            viewLifecycleOwner,
            Observer
            {
                getBinding().rvHourlyWeather.adapter = HourlyWeatherAdapter(it.hourlyForecast)
            })
    }


    private fun checkPermissions() {
        if (!isAccessCoarseLocationGranted(requireContext())) {
            requestAccessCoarseLocationPermission(
                requireActivity(),
                LOCATION_REQUEST_CODE
            )
        } else {
            initObservers()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            initObservers()
        } else {
            Snackbar.make(
                layout_home_fragment,
                getString(R.string.no_location_permission),
                Snackbar.LENGTH_SHORT
            )
        }
    }


}

//TODO: move permissions to activity