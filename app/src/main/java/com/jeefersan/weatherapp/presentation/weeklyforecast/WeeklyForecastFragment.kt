package com.jeefersan.weatherapp.presentation.weeklyforecast


import androidx.navigation.fragment.navArgs
import com.jeefersan.weatherapp.databinding.FragmentWeeklyForecastBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.weeklyforecast.viewmodels.WeeklyForecastViewModelImpl
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created by JeeferSan on 29-4-20.
 */
class WeeklyForecastFragment : BaseFragment<FragmentWeeklyForecastBinding>() {
    private val args: WeeklyForecastFragmentArgs by navArgs()

    private val viewModel: WeeklyForecastViewModelImpl by viewModel {
        parametersOf(
            args.weeklyForecastModel,
            args.locationName
        )
    }

    override val layoutId: Int = com.jeefersan.weatherapp.R.layout.fragment_weekly_forecast

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            rvWeeklyForecast.adapter = DailyWeatherAdapter()
        }
    }


}