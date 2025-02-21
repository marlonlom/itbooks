/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * New IT book detail entity data class.
 *
 * @author marlonlom
 *
 * @property isbn13 Book isbn13.
 * @property isbn10 Book isbn10.
 * @property title Book title.
 * @property subtitle Book subtitle.
 * @property detail Book details.
 * @property authors Book authors.
 * @property publisher Book publisher.
 * @property pages Book number of pages.
 * @property year Book published year.
 * @property rating Book rating.
 * @property language Book language.
 * @property price Book price.
 * @property picture Book picture url.
 */
@Entity("itb_new_book_detail")
data class NewBookDetailEntity(
  @PrimaryKey @ColumnInfo val isbn13: String,
  @ColumnInfo val isbn10: String,
  @ColumnInfo val title: String,
  @ColumnInfo val subtitle: String,
  @ColumnInfo val detail: String,
  @ColumnInfo val authors: String,
  @ColumnInfo val publisher: String,
  @ColumnInfo val pages: Int,
  @ColumnInfo val year: Int,
  @ColumnInfo val rating: Int,
  @ColumnInfo val language: String,
  @ColumnInfo val price: String,
  @ColumnInfo val picture: String,
)
