package com.jeefersan.usecases.location.searchlocations

import com.jeefersan.data.location.repositories.LocationRepository
import com.jeefersan.usecases.location.searchlocations.SearchLocationsUseCase


class SearchLocationsUseCaseImpl(private val locationRepository: LocationRepository) :
    SearchLocationsUseCase {
    override suspend fun invoke(query: String) =
        locationRepository.searchLocationsWithQuery(query)

}