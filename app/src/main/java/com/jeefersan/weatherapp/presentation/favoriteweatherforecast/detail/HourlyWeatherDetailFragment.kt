package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentDetailHourlyBinding
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.setDataHourlyRainVolume
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.setDataHourlyTemperature
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.setDataHumidity
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels.FavoriteForecastViewModelImpl
import kotlinx.android.synthetic.main.fragment_detail_hourly.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * Created by JeeferSan on 11-5-20.
 */
class HourlyWeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailHourlyBinding

    private val viewModel by lazy { requireParentFragment().getViewModel<FavoriteForecastViewModelImpl>() }

    companion object {
        private const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val detailFragment = HourlyWeatherDetailFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_hourly, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        var cityName = ""

        viewModel.cityName.observe(viewLifecycleOwner, Observer { name ->
            cityName = name
        })

        viewModel.hourlyForecastModel.observe(viewLifecycleOwner, Observer {
            when (position) {
                0 -> {
                    binding.barChart.visibility = View.GONE
                    lifecycleScope.launch {  setDataHourlyTemperature(it, binding.lineChart, cityName) }
                }
                1 -> {
                    binding.barChart.visibility = View.VISIBLE
                    binding.lineChart.visibility = View.GONE
                    lifecycleScope.launch {  setDataHourlyRainVolume(it, binding.barChart, cityName) }
                }
                else -> {
                    binding.lineChart.visibility = View.VISIBLE
                    binding.barChart.visibility = View.GONE
                    lifecycleScope.launch { setDataHumidity(it, binding.lineChart, cityName) }
                }
            }

        })
    }
}