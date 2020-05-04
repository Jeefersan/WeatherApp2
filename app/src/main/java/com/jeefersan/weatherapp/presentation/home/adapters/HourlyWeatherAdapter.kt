package com.jeefersan.weatherapp.presentation.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeefersan.weatherapp.databinding.ItemHourlyWeatherBinding
import com.jeefersan.weatherapp.misc.*
import com.jeefersan.weatherapp.models.HourlyWeatherModel
import com.jeefersan.weatherapp.models.getIconRes

/**
 * Created by JeeferSan on 28-4-20.
 */
class HourlyWeatherAdapter(private val items: List<HourlyWeatherModel>) :
    RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHourlyWeatherBinding.inflate(inflater, parent, false)
        return HourlyWeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(items[position])

    }

    inner class HourlyWeatherViewHolder(private val binding: ItemHourlyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(hourlyWeather: HourlyWeatherModel) {
            binding.apply {
                tvTime.text = hourlyWeather.timeStamp.toHourlyDate()
                ivWeatherIcon.setImageResource(hourlyWeather.getIconRes())
                ivWeatherIcon.setOnClickListener { it.setRotateAnimation() }
                tvTemperature.text = """${hourlyWeather.temperature}°C"""
                executePendingBindings()
            }

        }
    }


}

