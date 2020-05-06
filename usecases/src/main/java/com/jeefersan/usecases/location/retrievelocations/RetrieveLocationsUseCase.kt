package com.jeefersan.usecases.location.retrievelocations

import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 5-5-20.
 */
interface RetrieveLocationsUseCase {
    suspend operator fun invoke() : Flow<Result<List<Location>>>
}