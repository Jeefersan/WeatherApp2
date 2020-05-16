package com.jeefersan.weatherapp.presentation.favoriteweatherforecast.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.DialogCustomBinding
import com.jeefersan.weatherapp.misc.setRotateAnimation
import com.jeefersan.weatherapp.models.DailyWeatherModel

/**
 * Created by JeeferSan on 12-5-20.
 */
class DailyCustomDialog(private val dailyWeatherModel: DailyWeatherModel) : DialogFragment() {
    private lateinit var binding: DialogCustomBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_custom,
            null,
            false
        )
        binding.dailyWeather = dailyWeatherModel
        binding.ivIcon.setRotateAnimation()
        binding.btnClose.setOnClickListener { dismiss() }


        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setView(binding.root)
            .setCancelable(true)


        return builder.create()
    }
}