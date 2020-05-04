package com.jeefersan.data.unused.forecast.repositories

/**
 * Created by JeeferSan on 20-4-20.
 */

interface ForecastRepository<T> {
    suspend fun getForecastById(id: Int): T

    suspend fun getAllForecasts(): T
}

