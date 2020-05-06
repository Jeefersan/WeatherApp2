package com.jeefersan.weatherapp.presentation.weeklyforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeefersan.weatherapp.databinding.ListItemDailyWeatherBinding
import com.jeefersan.weatherapp.misc.BindableAdapter
import com.jeefersan.weatherapp.models.DailyWeatherModel

/**
 * Created by JeeferSan on 29-4-20.
 */
class DailyWeatherAdapter() : RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder>(),
    BindableAdapter<DailyWeatherModel> {
    private lateinit var mutableDataSet: MutableList<DailyWeatherModel>

    override fun getItemCount(): Int = mutableDataSet.size


    inner class DailyWeatherViewHolder(private val binding: ListItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dailyWeatherModel: DailyWeatherModel) {

            binding.apply {
                weatherModel = dailyWeatherModel
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemDailyWeatherBinding.inflate(inflater, parent, false)
        return DailyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(mutableDataSet[position])
    }

    override fun setData(models: List<DailyWeatherModel>) {
        mutableDataSet = models.toMutableList()
        notifyDataSetChanged()
    }
}