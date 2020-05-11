package com.jeefersan.usecases

/**
 * Created by JeeferSan on 8-5-20.
 */

fun shouldUpdateCurrent(lastCurrentUpdate: Long): Boolean =
    (System.currentTimeMillis() - lastCurrentUpdate) >= CURRENT_THRESHOLD


fun shouldUpdateForecast(lastForecastUpdate: Long): Boolean =
    (System.currentTimeMillis() - lastForecastUpdate) >= FORECAST_THRESHOLD


const val CURRENT_THRESHOLD = 2 * 10 * 60 * 1000

const val FORECAST_THRESHOLD = 5 * 10 * 60 * 1000