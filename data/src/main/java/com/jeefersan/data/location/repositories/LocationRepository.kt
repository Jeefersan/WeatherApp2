package com.jeefersan.data.location.repositories

import com.jeefersan.domain.Coordinates
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 23-4-20.
 */

interface LocationRepository {
    fun getCurrentLocation() : Flow<Result<Location>>

    suspend fun searchLocationsWithQuery(query: String)
    suspend fun retrieveSearchResult() : Flow<Result<List<Location>>>
}