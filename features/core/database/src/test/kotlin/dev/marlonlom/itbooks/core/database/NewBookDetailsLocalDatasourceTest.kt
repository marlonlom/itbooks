/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database

import dev.marlonlom.itbooks.core.database.books.detail.FakeNewBookDetailDao
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.FakeNewBookDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

internal class NewBookDetailsLocalDatasourceTest {

  private lateinit var dataSource: LocalDataSource

  @Before
  fun setup() {
    dataSource = LocalDataSourceImpl(
      newBookDao = FakeNewBookDao(),
      newBookDetailDao = FakeNewBookDetailDao(),
    )
  }

  @Test
  fun shouldNotFindBookDetailByIsbn13() = runBlocking {
    dataSource.findNewBooksDetail("uno").collect { book ->
      assertNull(book)
    }
  }

  @Test
  fun shouldFindBookDetailByIsbn13() = runBlocking {
    with(dataSource) {
      addNewBookDetails(
        listOf(
          NewBookDetailEntity(
            isbn13 = "01",
            title = "Book 01",
            price = "$ 12.34",
            picture = "http://www.notengo.com/photo.png",
            isbn10 = "001",
            subtitle = "Lorem ipsum",
            detail = "Lorem ipsum",
            authors = "Lorem ipsum",
            publisher = "Lorem ipsum",
            pages = 365,
            year = 2025,
            rating = 4,
            language = "English",
          ),
        ),
      )
      findNewBooksDetail("01").collect { book ->
        assertNotNull(book)
      }
    }
  }
}
