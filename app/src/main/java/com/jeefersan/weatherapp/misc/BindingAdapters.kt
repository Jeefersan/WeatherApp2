package com.jeefersan.weatherapp.misc

import android.animation.ValueAnimator
import android.graphics.Color
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.R.drawable
import com.jeefersan.weatherapp.presentation.base.LoadingStatus
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
    fun setWeatherIcon(view: ImageView, icon: String?) {
        if (icon != null) {
            Glide.with(view.context)
                .load(getOpenWeatherIconRes(icon))
                .centerCrop()
                .into(view)
        }

    }

    //android:text="@{@string/binding_temp(currentWeather.currentTemp)}"

    @BindingAdapter("tempAnimation")
    @JvmStatic
    fun setAnimation(textView: TextView, finalValue: Int){
        val startValue = (finalValue / 3)
        ValueAnimator.ofInt(startValue, finalValue)
            .apply {
                duration = 2000
                addUpdateListener { textView.text = it.animatedValue.toString() + "°C" }
                start()
            }
    }



    @BindingAdapter("setWeatherIcon")
    @JvmStatic
    fun setIcon(view: ImageView, icon: String) {
        Glide.with(view.context)
            .load(getWeatherIconResFromInt(icon))
            .centerCrop()
            .into(view)
    }


    @BindingAdapter("setIcon")
    @JvmStatic
    fun setIconDefault(view: ImageView, icon: String) {
        view.setImageResource(getWeatherIconResFromInt(icon))
    }


    @BindingAdapter("dayNightTextColor")
    @JvmStatic
    fun setTextColor(textView: TextView, isSunset: Boolean) {
        if (isSunset) {
            textView.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            textView.setTextColor(Color.parseColor("#303C3E"))
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

    @BindingAdapter("webStatus")
    @JvmStatic
    fun bindWebStatus(statusImageView: ImageView, status: LoadingStatus?) {
        when (status) {
            LoadingStatus.LOADING -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.loading_web)
            }
            LoadingStatus.ERROR -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.ic_error_black_24dp)
            }
            LoadingStatus.DONE -> {
                statusImageView.visibility = View.GONE
            }
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
        if (list.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("webViewClient")
    @JvmStatic
    fun setWebViewClient(webView: WebView, webViewClient: WebViewClient) {
        webView.webViewClient = webViewClient
    }


    @BindingAdapter("webUri")
    @JvmStatic
    fun WebView.loadRepoUrl(url: String) {
        run { loadUrl(url) }
    }


}

object Converter {

    @BindingAdapter("dateToWeekDay")
    @JvmStatic
    fun dateToWeekDay(tv: TextView, timestamp: Long) {
        tv.text = String.format(Locale.getDefault(), "%tA", timestamp * 1000L).subSequence(0, 3)

    }


}

interface BindableAdapter<T> {
    fun setData(models: List<T>)
}
