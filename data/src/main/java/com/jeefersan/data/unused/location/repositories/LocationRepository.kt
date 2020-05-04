package com.jeefersan.data.unused.location.repositories

import com.jeefersan.domain.Coordinates
import com.jeefersan.util.Result

/**
 * Created by JeeferSan on 23-4-20.
 */

interface LocationRepository {
    suspend fun getCurrentLocation(): Result<Coordinates>
}