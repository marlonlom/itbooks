/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.list

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * New IT books list dao interface.
 *
 * @author marlonlom
 */
@Dao
interface NewBookDao {

  /**
   * List all new books.
   *
   * @return Books list as Flow.
   */
  @Query("SELECT * FROM itb_new_book")
  fun listAll(): Flow<List<NewBookEntity>>

  /**
   * Inserts or updates [entities] in the db under the specified primary keys
   *
   * @param entities List of entities to be inserted or updated.
   */
  @Upsert
  suspend fun upsertBooks(entities: List<NewBookEntity>)
}
