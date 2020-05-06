package com.jeefersan.weatherapp.framework.di

import com.algolia.search.client.ClientPlaces
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID

/**
 * Created by JeeferSan on 6-5-20.
 */

fun providePlacesClient(): ClientPlaces {
    val client = ClientPlaces(
        ApplicationID("plA2HD70HUT8"), APIKey("94134bf29dd613c3f1052eb7888d8158")
    )
    return client
}
