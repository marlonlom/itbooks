/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class BookDetailsItemTest {

  @Test
  fun `Should create instance using book detail database entity`() {
    val entity = NewBookDetailEntity(
      isbn13 = "9781098111878",
      isbn10 = "1098111877",
      title = "Data Visualization with Python and JavaScript, 2nd Edition",
      subtitle = "Scrape, Clean, Explore, and Transform Your Data",
      detail = "How do you turn raw, unprocessed, or malformed data into dynamic, " +
        "interactive web visualizations? In this practical book, author Kyran Dale " +
        "shows data scientists and analysts-as well as Python and JavaScript developers-how " +
        "to create the ideal toolchain for the job. By providing engaging examples and...",
      authors = "Kyran Dale",
      publisher = "O'Reilly Media",
      language = "English",
      pages = 566,
      year = 2022,
      rating = 4,
      price = "$60.99",
      picture = "https://itbook.store/img/books/9781098111878.png",
    )

    val bookDetailsItem = BookDetailsItem.fromEntity(entity)
    assertNotNull(bookDetailsItem)
    assertEquals(entity.isbn13, bookDetailsItem.isbn13)
    assertEquals(entity.isbn10, bookDetailsItem.isbn10)
    assertEquals(entity.title, bookDetailsItem.title)
    assertEquals(entity.subtitle, bookDetailsItem.subtitle)
    assertEquals(entity.detail, bookDetailsItem.detail)
    assertEquals(entity.pages, bookDetailsItem.pages)
    assertEquals(entity.year, bookDetailsItem.year)
    assertEquals(entity.publisher, bookDetailsItem.publisher)
    assertEquals(entity.authors, bookDetailsItem.authors)
    assertEquals(entity.language, bookDetailsItem.language)
    assertEquals(entity.rating, bookDetailsItem.rating)
    assertEquals(entity.price, bookDetailsItem.price)
    assertEquals(entity.picture, bookDetailsItem.picture)
    assertFalse(bookDetailsItem.isFree)
  }

  @Test
  fun `Should create instance using book list database entity with zero price`() {
    val entity = NewBookDetailEntity(
      title = "Azure Maps Using Blazor Succinctly",
      subtitle = "",
      authors = "Michael Washington",
      publisher = "Syncfusion",
      language = "English",
      isbn10 = "1642002267",
      isbn13 = "9781642002263",
      pages = 92,
      year = 2022,
      rating = 0,
      detail = "Microsoft Azure Maps is part of Microsoft Azure Cloud Services and " +
        "provides a wide range of powerful geospatial capabilities and a rich set of " +
        "REST APIs. It has SDKs for both web and mobile applications. In Azure Maps Using " +
        "Blazor Succinctly, learn how you can create sophisticated applications with ...",
      price = "$0.00",
      picture = "https://itbook.store/img/books/9781642002263.png",
    )

    val bookDetailsItem = BookDetailsItem.fromEntity(entity)
    assertNotNull(bookDetailsItem)
    assertEquals(entity.isbn13, bookDetailsItem.isbn13)
    assertEquals(entity.isbn10, bookDetailsItem.isbn10)
    assertEquals(entity.title, bookDetailsItem.title)
    assertEquals(entity.subtitle, bookDetailsItem.subtitle)
    assertEquals(entity.detail, bookDetailsItem.detail)
    assertEquals(entity.pages, bookDetailsItem.pages)
    assertEquals(entity.year, bookDetailsItem.year)
    assertEquals(entity.publisher, bookDetailsItem.publisher)
    assertEquals(entity.authors, bookDetailsItem.authors)
    assertEquals(entity.language, bookDetailsItem.language)
    assertEquals(entity.rating, bookDetailsItem.rating)
    assertEquals(entity.price, bookDetailsItem.price)
    assertEquals(entity.picture, bookDetailsItem.picture)
    assertTrue(bookDetailsItem.isFree)
  }
}
