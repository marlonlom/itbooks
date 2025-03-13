/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import dev.marlonlom.itbooks.core.database.LocalDataSource
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class BooksListRepositoryTest {

  private lateinit var repository: BooksListRepository

  @Test
  fun `Should fetch empty books list`() = runTest {
    val datasource = object : LocalDataSource {
      override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(emptyList())
      override suspend fun addNewBooks(books: List<NewBookEntity>) {}
      override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf()
      override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
    }
    repository = BooksListRepository(datasource)
    repository.fetchBooks().collect { books ->
      assertNotNull(books)
      assertTrue(books.isEmpty())
    }
  }

  @Test
  fun `Should fetch non empty books list`() = runTest {
    val datasource = object : LocalDataSource {
      override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(
        listOf(
          NewBookEntity(
            isbn13 = "9781491954249",
            title = "Designing Across Senses",
            price = "$27.59",
            picture = "https://itbook.store/img/books/9781491954249.png",
          ),
          NewBookEntity(
            isbn13 = "9781642002263",
            title = "Azure Maps Using Blazor Succinctly",
            price = "$0.00",
            picture = "https://itbook.store/img/books/9781642002263.png",
          ),
        ),
      )

      override suspend fun addNewBooks(books: List<NewBookEntity>) {}
      override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf()
      override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
    }
    repository = BooksListRepository(datasource)
    repository.fetchBooks().collect { books ->
      assertNotNull(books)
      assertTrue(books.isNotEmpty())
      assertEquals(2, books.size)
    }
  }
}
