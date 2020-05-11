package com.jeefersan.weatherapp.presentation.base

import android.view.View

/**
 * Created by JeeferSan on 6-5-20.
 */

interface BaseItemListener<T>{
    fun onItemClick(item: T)
}

interface BaseClickListener<T>{
    fun onClick(id: T)
}