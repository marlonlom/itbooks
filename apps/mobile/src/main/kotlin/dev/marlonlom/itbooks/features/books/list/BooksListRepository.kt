/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import dev.marlonlom.itbooks.core.database.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * IT Books list repository class.
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source dependency.
 */
class BooksListRepository(private val localDataSource: LocalDataSource) {

  /**
   * Returns new books list from local datasource.
   *
   * @return books list as Flow.
   */
  fun fetchBooks(): Flow<List<BooksListItem>> = localDataSource.listNewBooks()
    .map { entities -> entities.map { BooksListItem.fromEntity(it) } }
}
