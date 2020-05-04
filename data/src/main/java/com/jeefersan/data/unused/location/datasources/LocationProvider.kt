package com.jeefersan.data.unused.location.datasources

import com.jeefersan.domain.Coordinates
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 26-4-20.
 */
interface LocationProvider {
    suspend fun getCurrentCoordinates(): Result<Coordinates>
}