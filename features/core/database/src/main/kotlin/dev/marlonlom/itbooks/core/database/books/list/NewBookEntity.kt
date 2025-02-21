/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * New IT book entity data class.
 *
 * @author marlonlom
 *
 * @property isbn13 Book isbn13.
 * @property title Book title.
 * @property price Book price.
 * @property picture Book picture url.
 */
@Entity("itb_new_book")
data class NewBookEntity(
  @PrimaryKey
  @ColumnInfo val isbn13: String,
  @ColumnInfo val title: String,
  @ColumnInfo val price: String,
  @ColumnInfo val picture: String,
)
