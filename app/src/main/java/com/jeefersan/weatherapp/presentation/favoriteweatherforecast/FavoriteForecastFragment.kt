package com.jeefersan.weatherapp.presentation.favoriteweatherforecast

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.github.matteobattilana.weather.PrecipType
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentFavoriteWeatherForecastBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels.FavoriteForecastViewModelImpl
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_favorite_weather_forecast.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by JeeferSan on 8-5-20.
 */
class FavoriteForecastFragment : BaseFragment<FragmentFavoriteWeatherForecastBinding>() {
    private val args: FavoriteForecastFragmentArgs by navArgs()

    private val viewModel: FavoriteForecastViewModelImpl by viewModel { parametersOf(args.favoriteId) }

    override val layoutId: Int =
        com.jeefersan.weatherapp.R.layout.fragment_favorite_weather_forecast

    override fun getViewModel(): BaseViewModel = viewModel


    override fun setupBinding() {
        getBinding().apply {
            lifecycleOwner = this@FavoriteForecastFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }



    override fun initObservers() {
        super.initObservers()
        viewModel.weatherType.observe(viewLifecycleOwner, Observer { type ->

        })
        viewModel.weeklyForecastModel.observe(viewLifecycleOwner, Observer { weeklyForecast ->

        })
    }
}

