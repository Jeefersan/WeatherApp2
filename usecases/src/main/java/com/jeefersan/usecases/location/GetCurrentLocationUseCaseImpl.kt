package com.jeefersan.usecases.location

import com.jeefersan.data.flowlocation.FlowLocationProvider
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

/**
 * Created by JeeferSan on 28-4-20.
 */

@ExperimentalCoroutinesApi
class GetCurrentLocationUseCaseImpl(private val flowLocationProvider: FlowLocationProvider) :
    GetCurrentLocationUseCase {
    override fun invoke(): Flow<Result<Location>> =
        flowLocationProvider.getLocation().map { result ->
            if (result is Result.Success) {
                Result.Success(result.data)
            } else {
                Result.Failure(Throwable("Location error"))
            }
        }
            .catch { t -> emit(Result.Failure(t)) }
            .flowOn(Dispatchers.IO)

}