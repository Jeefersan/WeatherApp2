package com.jeefersan.data.weatherforecast.util

/**
 * Created by JeeferSan on 3-5-20.
 */

const val THRESHOLD = 3 * 10 * 60 * 1000

fun shouldUpdate(lastUpdateTime: Long): Boolean {
    return (System.currentTimeMillis() - lastUpdateTime) >= THRESHOLD
}