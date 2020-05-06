package com.jeefersan.weatherapp.framework.location

import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.helper.searchbox.SearchBoxConnector
import com.algolia.instantsearch.helper.searcher.SearcherPlaces
import com.algolia.search.client.ClientPlaces
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.places.PlaceLanguage
import com.algolia.search.model.places.PlaceType
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.KeyAlgoliaAPIKey
import com.algolia.search.transport.RequestOptions
import com.jeefersan.data.location.LocationSearcher
import com.jeefersan.domain.Location
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.presentation.search.PlacesAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.internal.ChannelFlow

/**
 * Created by JeeferSan on 5-5-20.
 */

@FlowPreview
@ExperimentalCoroutinesApi
class LocationSearcherImpl(private val client: ClientPlaces) : LocationSearcher {

    val channel = ConflatedBroadcastChannel<Result<List<Location>>>()


    override suspend fun queryLocations(query: String) {
        val response = client.searchPlaces(
            query = PlacesQuery(query),
            language = Language.English
        )

        if (!channel.isClosedForSend) {
            channel.offer(Result.Success(response.hits.mapToDomain()))
        }
    }

    override suspend fun retrieveLocations(): Flow<Result<List<Location>>> =
        channel.asFlow()
}

