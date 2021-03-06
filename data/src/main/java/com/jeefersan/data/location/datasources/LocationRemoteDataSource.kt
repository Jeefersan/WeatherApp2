package com.jeefersan.data.location.datasources

import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 5-5-20.
 */
interface LocationRemoteDataSource {
    suspend fun queryLocations(query: String)
    suspend fun retrieveLocations(): Flow<Result<List<Location>>>
}