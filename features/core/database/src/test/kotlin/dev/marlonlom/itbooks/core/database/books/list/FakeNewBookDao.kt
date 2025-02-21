/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fake new books list dao.
 *
 * @author marlonlom
 *
 * @property list mutable books list.
 */
class FakeNewBookDao(private val list: MutableList<NewBookEntity> = mutableListOf()) : NewBookDao {

  override fun listAll(): Flow<List<NewBookEntity>> = flowOf(list)

  override suspend fun upsertBooks(entities: List<NewBookEntity>) {
    list.addAll(entities)
  }
}
