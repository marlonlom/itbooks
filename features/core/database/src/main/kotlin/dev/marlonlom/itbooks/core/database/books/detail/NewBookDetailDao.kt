/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.detail

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * New IT book details dao interface.
 *
 * @author marlonlom
 */
@Dao
interface NewBookDetailDao {

  /**
   * Finds book detail using its isbn13.
   *
   * @param isbn13 book isbn13.
   *
   * @return Book detail as Flow.
   */
  @Query("SELECT * FROM itb_new_book_detail WHERE isbn13=:isbn13")
  fun findByIsbn13(isbn13: String): Flow<NewBookDetailEntity?>

  /**
   * Inserts or updates [entities] in the db under the specified primary keys
   *
   * @param entities List of entities to be inserted or updated.
   */
  @Upsert
  suspend fun upsertBooks(entities: List<NewBookDetailEntity>)
}
