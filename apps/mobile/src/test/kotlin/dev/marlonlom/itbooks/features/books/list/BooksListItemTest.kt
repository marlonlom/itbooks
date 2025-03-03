/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class BooksListItemTest {

  @Test
  fun `Should create instance using book list database entity`() {
    val entity = NewBookEntity(
      isbn13 = "9781491954249",
      title = "Designing Across Senses",
      price = "$27.59",
      picture = "https://itbook.store/img/books/9781491954249.png",
    )
    val booksListItem = BooksListItem.fromEntity(entity)
    assertNotNull(booksListItem)
    assertEquals(entity.isbn13, booksListItem.isbn13)
    assertEquals(entity.title, booksListItem.title)
    assertEquals(entity.price, booksListItem.price)
    assertFalse(booksListItem.isFree)
    assertEquals(entity.picture, booksListItem.picture)
  }

  @Test
  fun `Should create instance using book list database entity with zero price`() {
    val entity = NewBookEntity(
      isbn13 = "9781642002263",
      title = "Azure Maps Using Blazor Succinctly",
      price = "$0.00",
      picture = "https://itbook.store/img/books/9781642002263.png",
    )
    val booksListItem = BooksListItem.fromEntity(entity)
    assertNotNull(booksListItem)
    assertEquals(entity.isbn13, booksListItem.isbn13)
    assertEquals(entity.title, booksListItem.title)
    assertEquals("$0.00", booksListItem.price)
    assertTrue(booksListItem.isFree)
    assertEquals(entity.picture, booksListItem.picture)
  }
}
