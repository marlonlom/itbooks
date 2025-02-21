/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.di

import dev.marlonlom.itbooks.core.database.ItBooksDatabase
import dev.marlonlom.itbooks.core.database.LocalDataSource
import dev.marlonlom.itbooks.core.database.LocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Database Koin module.
 *
 * @author marlonlom
 */
val databaseKoinModule = module {
  single<LocalDataSource> {
    ItBooksDatabase.getInstance(androidContext()).let { db ->
      LocalDataSourceImpl(
        newBookDao = db.newBookDao(),
        newBookDetailDao = db.newBookDetailDao(),
      )
    }
  } bind LocalDataSource::class
}
