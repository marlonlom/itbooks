/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database

import dev.marlonlom.itbooks.core.database.books.detail.FakeNewBookDetailDao
import dev.marlonlom.itbooks.core.database.books.list.FakeNewBookDao
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class NewBooksLocalDatasourceTest {

  private lateinit var dataSource: LocalDataSource

  @Before
  fun setup() {
    dataSource = LocalDataSourceImpl(
      newBookDao = FakeNewBookDao(),
      newBookDetailDao = FakeNewBookDetailDao(),
    )
  }

  @Test
  fun shouldDisplayEmptyBooksList() = runBlocking {
    dataSource.listNewBooks().collect { books ->
      assertNotNull(books)
      assertTrue(books.isEmpty())
    }
  }

  @Test
  fun shouldDisplayNonEmptyBooksList() = runBlocking {
    with(dataSource) {
      addNewBooks(
        listOf(
          NewBookEntity(
            isbn13 = "01",
            title = "Book 01",
            price = "$ 12.34",
            picture = "http://www.notengo.com/photo.png",
          ),
        ),
      )
      listNewBooks().collect { books ->
        assertNotNull(books)
        assertFalse(books.isEmpty())
        assertEquals(1, books.size)
      }
    }
  }
}
