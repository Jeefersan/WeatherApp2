package com.jeefersan.weatherapp.framework.location

import com.algolia.search.client.ClientPlaces
import com.algolia.search.dsl.placesQuery
import com.algolia.search.model.places.PlaceType
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.search.Language
import com.jeefersan.data.location.datasources.LocationRemoteDataSource
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * Created by JeeferSan on 5-5-20.
 */

@FlowPreview
@ExperimentalCoroutinesApi
class LocationRemoteDataSourceImpl(private val client: ClientPlaces) :
    LocationRemoteDataSource {

    val channel = ConflatedBroadcastChannel<Result<List<Location>>>()


    override suspend fun queryLocations(query: String) {
        val response = client.searchPlaces(
            query = PlacesQuery(query, PlaceType.City),
            language = Language.English
        )


        if (!channel.isClosedForSend) {
            channel.offer(Result.Success(response.hits.mapToDomain()))
        }
    }

    override suspend fun retrieveLocations(): Flow<Result<List<Location>>> =
        channel.asFlow()
}

