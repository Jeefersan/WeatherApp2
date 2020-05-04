package com.jeefersan.data.flowlocation

import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 28-4-20.
 */

interface FlowLocationProvider {
    fun getLocation(): Flow<Result<Location>>

}