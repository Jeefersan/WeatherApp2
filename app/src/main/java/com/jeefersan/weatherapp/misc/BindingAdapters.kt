package com.jeefersan.weatherapp.misc

import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeefersan.domain.Location
import com.jeefersan.weatherapp.R.drawable
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
import java.lang.String.format
import java.text.DateFormat
import java.util.*

/**
 * Created by JeeferSan on 29-4-20.
 */

object BindingAdapters {
    @BindingAdapter("items")
    @JvmStatic
    fun setListItems(rv: RecyclerView, items: List<Any>) {

        (rv.adapter as? BindableAdapter<Any>)?.setData(items)
    }


    @BindingAdapter("weatherIcon")
    @JvmStatic
    fun setWeatherIcon(view: ImageView, icon: String) {

        Glide.with(view.context)
            .load(getOpenWeatherIconRes(icon))
            .centerCrop()
            .into(view)

    }

    @BindingAdapter("setWeatherIcon")
    @JvmStatic
    fun setIcon(view: ImageView, icon: String) {
        Glide.with(view.context)
            .load(getWeatherIconResFromInt(icon))
            .centerCrop()
            .into(view)
    }


    @BindingAdapter("dayNightTextColor")
    @JvmStatic
    fun setTextColor(textView: TextView, isSunset: Boolean) {
        if (isSunset) {
            textView.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            textView.setTextColor(Color.parseColor("#A6A4A4"))
        }
    }

    @BindingAdapter("isSunset")
    @JvmStatic
    fun setBackground(view: View, isSunset: Boolean) {
        if (isSunset) {
            view.setBackgroundResource(drawable.bg_night)
        } else {
            view.setBackgroundResource(drawable.bg_sunny)
        }
    }

    @BindingAdapter("progressbarVisibility")
    @JvmStatic
    fun setVisibility(view: View, status: LoadingStatus) {
        when (status) {
            LoadingStatus.LOADING -> view.visibility = View.VISIBLE
            else -> view.visibility = View.GONE
        }
    }

    @BindingAdapter("viewVisibility")
    @JvmStatic
    fun setVisible(view: View, status: LoadingStatus) {
        when (status) {
            LoadingStatus.DONE -> view.visibility = View.VISIBLE
            else -> view.visibility = View.GONE
        }
    }

    @BindingAdapter("visibleIfEmpty")
    @JvmStatic
    fun visibleIf(view: View, list: List<Any>) {
        if(list.isNullOrEmpty()){ view.visibility = View.VISIBLE}
        else {
            view.visibility = View.GONE
        }
    }



}

    object Converter {

        @BindingAdapter("dateToWeekDay")
        @JvmStatic
        fun dateToWeekDay(tv: TextView, timestamp: Long) {
            tv.text = String.format(Locale.getDefault(), "%tA", timestamp * 1000L).subSequence(0,3)

        }


    }

    interface BindableAdapter<T> {
        fun setData(models: List<T>)
    }
