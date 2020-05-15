package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentDetailDailyBinding
import com.jeefersan.weatherapp.models.DailyWeatherModel
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.setDataDaily
import com.jeefersan.weatherapp.presentation.favoriteweatherforecast.viewmodels.FavoriteForecastViewModelImpl
import com.jeefersan.weatherapp.presentation.weeklyforecast.DailyWeatherAdapter
import kotlinx.android.synthetic.main.fragment_detail_daily.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * Created by JeeferSan on 11-5-20.
 */
class DailyWeatherDetailFragment : Fragment(), OnChartValueSelectedListener {
    private lateinit var binding: FragmentDetailDailyBinding
    private lateinit var dailyWeatherAdapter: DailyWeatherAdapter
    private lateinit var dailyWeatherList: List<DailyWeatherModel>

    private val viewModel by lazy { requireParentFragment().getViewModel<FavoriteForecastViewModelImpl>() }

    companion object {
        private const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val dailyDetailFragment = DailyWeatherDetailFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            dailyDetailFragment.arguments = bundle
            return dailyDetailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_daily, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        viewModel.weeklyForecastModel.observe(viewLifecycleOwner, Observer { list ->
            when (position) {
                0 -> {
                    lifecycleScope.launch {
                        binding.rvDaily.visibility = View.VISIBLE
                        binding.lineChart.visibility = View.GONE
                        dailyWeatherAdapter = DailyWeatherAdapter()
                        dailyWeatherAdapter.setData(list)
                        rv_daily.adapter = dailyWeatherAdapter
                    }
                }
                else -> {
                    lifecycleScope.launch {
                        binding.rvDaily.visibility = View.GONE
                        dailyWeatherList = list

                        binding.lineChart.visibility = View.VISIBLE
                        lifecycleScope.launch {
                            setDataDaily(list, binding.lineChart, requireContext())
                            binding.lineChart.setOnChartValueSelectedListener(this@DailyWeatherDetailFragment)
                        }
                    }
                }
            }
        })
    }

    override fun onNothingSelected() {
        Log.d("Ok", "Do Nothing")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.d("DailyDetail", "entry selected value is ${e?.data}")
        viewModel.onDailyWeatherclick(e?.data as Int)
    }
}