package com.jeefersan.weatherapp.favorites

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jeefersan.data.favorites.repositories.FavoritesRepository
import com.jeefersan.domain.Favorite
import com.jeefersan.util.Result
import com.jeefersan.weatherapp.framework.di.roomTestModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Created by JeeferSan on 7-5-20.
 */


@RunWith(AndroidJUnit4::class)
class FavoritesRepositoryTest : KoinTest {

    val favoritesRepository: FavoritesRepository by inject()

    @Before
    fun before() {
        loadKoinModules(module { single(override = true){ roomTestModule} })
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testReadAndWrite() =

        runBlocking {
            val favorite = Favorite(99, "Utrecht", 2.5050, 3.505, System.currentTimeMillis())

            val id = ((favoritesRepository.addFavorite(favorite)) as Result.Success).data

            val favoriteResult = (favoritesRepository.getFavoriteById(id) as Result.Success).data

            Assert.assertEquals(favorite.cityName, favoriteResult.cityName)
            Assert.assertEquals(favorite.favoriteId, favoriteResult.favoriteId)

        }


}