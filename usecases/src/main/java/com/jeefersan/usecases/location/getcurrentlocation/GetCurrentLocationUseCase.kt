package com.jeefersan.usecases.location.getcurrentlocation

import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 28-4-20.
 */

@ExperimentalCoroutinesApi
interface GetCurrentLocationUseCase {
    operator fun invoke(): Flow<Result<Location>>
}