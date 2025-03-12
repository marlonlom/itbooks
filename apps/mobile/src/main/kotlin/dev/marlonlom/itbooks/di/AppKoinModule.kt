/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.di

import dev.marlonlom.itbooks.core.database.di.databaseKoinModule
import dev.marlonlom.itbooks.features.books.detail.BookDetailsRepository
import dev.marlonlom.itbooks.features.books.detail.BookDetailsViewModel
import dev.marlonlom.itbooks.features.books.list.BooksListRepository
import dev.marlonlom.itbooks.features.books.list.BooksListViewModel
import dev.marlonlom.itbooks.ui.main.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Main mobile application Koin module.
 *
 * @author marlonlom
 */
val appKoinModule = module {
  includes(databaseKoinModule)

  viewModelOf(::MainActivityViewModel)

  viewModelOf(::BooksListViewModel)
  single {
    BooksListRepository(get(), Dispatchers.IO)
  }

  viewModelOf(::BookDetailsViewModel)
  single {
    BookDetailsRepository(get(), Dispatchers.IO)
  }
}
