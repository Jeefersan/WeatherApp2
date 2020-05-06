package com.jeefersan.data.location.datasources

import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 28-4-20.
 */

interface LocationLocalDataSource {
    fun getCurrentLocation(): Flow<Result<Location>>

}