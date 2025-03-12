/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity

/**
 * Book details ui item data class.
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
data class BookDetailsItem(
  val isbn13: String,
  val isbn10: String,
  val title: String,
  val subtitle: String,
  val detail: String,
  val authors: String,
  val publisher: String,
  val pages: Int,
  val year: Int,
  val rating: Int,
  val language: String,
  val price: String,
  val picture: String,
) {

  /** Returns True/False if book price is Zero. */
  val isFree: Boolean get() = price == "$0.00"

  companion object {

    /**
     * Creates a new [BookDetailsItem] from [NewBookDetailEntity] database entity.
     *
     * @param entity Database entity
     * @return New instance of [BookDetailsItem] from database entity.
     */
    @JvmStatic
    fun fromEntity(entity: NewBookDetailEntity): BookDetailsItem = BookDetailsItem(
      isbn13 = entity.isbn13,
      isbn10 = entity.isbn10,
      title = entity.title,
      subtitle = entity.subtitle,
      detail = entity.detail,
      authors = entity.authors,
      publisher = entity.publisher,
      pages = entity.pages,
      year = entity.year,
      rating = entity.rating,
      language = entity.language,
      price = entity.price,
      picture = entity.picture,
    )
  }
}
