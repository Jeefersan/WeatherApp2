package com.jeefersan.data.unused.forecast.datasources

import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Forecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 22-4-20.
 */
interface ForecastDataSource {
    suspend fun getByCoordinates(coordinates: Coordinates) : Result<Forecast>
    suspend fun getByCityId(id: Long): Result<Forecast>
    suspend fun getAll(): Result<List<Forecast>>
}