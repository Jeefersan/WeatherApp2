package com.jeefersan.weatherapp.framework.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeefersan.weatherapp.framework.db.LocalDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by JeeferSan on 5-5-20.
 */

@ExperimentalCoroutinesApi
val roomTestModule = module {
    factory {
        Room.inMemoryDatabaseBuilder(androidContext(), LocalDatabase::class.java)
            .setTransactionExecutor(TestCoroutineDispatcher().asExecutor())
            .setQueryExecutor(TestCoroutineDispatcher().asExecutor())
            .allowMainThreadQueries()
            .build()
    }
}
