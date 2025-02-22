/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.compose.runtime.Immutable
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity

/**
 * IT Book list ui item data class.
 *
 * @author marlonlom
 *
 * @property isbn13 Book isbn13.
 * @property title Book title.
 * @property price Book price text.
 * @property picture Book picture url.
 */
@Immutable
data class BookListItem(val isbn13: String, val title: String, val price: String, val picture: String) {

  companion object {

    /**
     * Creates a new [BookListItem] from [NewBookEntity] database entity.
     *
     * @param entity Database entity
     * @return New instance of [BookListItem] from database entity.
     */
    @JvmStatic
    fun fromEntity(entity: NewBookEntity): BookListItem = BookListItem(
      isbn13 = entity.isbn13,
      title = entity.title,
      picture = entity.picture,
      price = when (entity.price) {
        "$0.00" -> "Free"
        else -> entity.price
      },
    )
  }
}
