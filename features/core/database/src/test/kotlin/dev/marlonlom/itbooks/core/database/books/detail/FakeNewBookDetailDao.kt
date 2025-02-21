/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.detail

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake new book details dao.
 *
 * @author marlonlom
 *
 * @property list mutable book details list.
 */
internal class FakeNewBookDetailDao(private val list: MutableList<NewBookDetailEntity> = mutableListOf()) :
  NewBookDetailDao {

  override fun findByIsbn13(isbn13: String): Flow<NewBookDetailEntity?> = flowOf(list.find { it.isbn13 == isbn13 })

  override suspend fun upsertBooks(entities: List<NewBookDetailEntity>) {
    list.addAll(entities)
  }
}
