package com.jeefersan.usecases.location.retrievelocations

import com.jeefersan.data.location.repositories.LocationRepository
import com.jeefersan.domain.Location
import com.jeefersan.usecases.location.retrievelocations.RetrieveLocationsUseCase
import com.jeefersan.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class RetrieveLocationsUseCaseImpl(private val locationRepository: LocationRepository) :
    RetrieveLocationsUseCase {
    @ExperimentalCoroutinesApi
    override suspend fun invoke(): Flow<Result<List<Location>>> =
        locationRepository.retrieveSearchResult()
            .map {
                if (it is Result.Success) {
                    Result.Success(it.data)
                } else {
                    Result.Failure(Throwable("No values"))
                }
            }
            .catch { t -> emit(Result.Failure(t)) }
            .flowOn(Dispatchers.Default)

}
