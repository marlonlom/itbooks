/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import dev.marlonlom.itbooks.core.database.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

/**
 * IT Book details repository class.
 *
 * @author marlonlom
 *
 * @property localDataSource Local data source dependency.
 * @property coroutineDispatcher Coroutine dispatcher.
 */
class BookDetailsRepository(
  private val localDataSource: LocalDataSource,
  private val coroutineDispatcher: CoroutineContext = Dispatchers.IO,
) {

  /**
   * Search book detailed information for provided isbn13.
   *
   * @param isbn13 Book isbn13.
   *
   * @return Book detailed information, or null, as Flow
   */
  fun find(isbn13: String): Flow<BookDetailsItem?> = localDataSource.findNewBooksDetail(isbn13)
    .map { it?.let { entity -> BookDetailsItem.fromEntity(entity) } }
    .flowOn(coroutineDispatcher)
}
