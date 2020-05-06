package com.jeefersan.usecases.location.searchlocations

import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by JeeferSan on 5-5-20.
 */

interface SearchLocationsUseCase {
    suspend operator fun invoke(query: String)
}