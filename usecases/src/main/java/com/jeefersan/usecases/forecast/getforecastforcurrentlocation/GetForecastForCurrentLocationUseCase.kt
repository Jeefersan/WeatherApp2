package com.jeefersan.usecases.forecast.getforecastforcurrentlocation

import com.jeefersan.domain.Forecast
import com.jeefersan.domain.Location
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 22-4-20.
 */
interface GetForecastForCurrentLocationUseCase {
    suspend operator fun invoke(location: Location): Result<Forecast>
}