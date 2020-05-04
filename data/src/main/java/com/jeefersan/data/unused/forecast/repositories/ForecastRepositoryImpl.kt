package com.jeefersan.data.unused.forecast.repositories

import com.jeefersan.data.unused.forecast.datasources.local.ForecastLocalDataSource
import com.jeefersan.data.unused.forecast.datasources.remote.ForecastRemoteDataSource
import com.jeefersan.domain.Forecast
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 20-4-20.
 */
class ForecastRepositoryImpl(
    private val localDataSource: ForecastLocalDataSource,
    private val remoteDataSource: ForecastRemoteDataSource
) : ForecastRepository<Result<Forecast>> {

    override suspend fun getForecastById(id: Int): Result<Forecast> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllForecasts(): Result<Forecast> {
        TODO("Not yet implemented")
    }
}