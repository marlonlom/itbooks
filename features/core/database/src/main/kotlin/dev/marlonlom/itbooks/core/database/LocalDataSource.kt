/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database

import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import kotlinx.coroutines.flow.Flow

/**
 * Local data source interface definition.
 *
 * @author marlonlom
 */
interface LocalDataSource {

  /**
   * Return a list with new books.
   *
   * @return New books list as Flow.
   */
  fun listNewBooks(): Flow<List<NewBookEntity>>

  /**
   * Insert new books list.
   *
   * @param books new books list.
   */
  suspend fun addNewBooks(books: List<NewBookEntity>)

  /**
   * Return an it book detail by using its isbn13.
   *
   * @return Books detail, or null, as Flow.
   */
  fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?>

  /**
   * Insert new book details list.
   *
   * @param books new book details list.
   */
  suspend fun addNewBookDetails(books: List<NewBookDetailEntity>)
}
