package com.jeefersan.usecases.location

import com.jeefersan.data.location.LocationSearcher
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
class SearchLocationsUseCaseImpl(private val locationSearcher: LocationSearcher) :
    SearchLocationsUseCase {
    override suspend fun invoke(query: String): Flow<Result<List<Location>>> {
        locationSearcher.queryLocations(query)

        return locationSearcher.retrieveLocations()
            .map {
                if (it is Result.Success) {
                    Result.Success(it.data)
                } else {
                    Result.Failure(Throwable("No values"))
                }
            }
            .catch { t -> emit(Result.Failure(t)) }
            .flowOn(Dispatchers.IO)
    }
}