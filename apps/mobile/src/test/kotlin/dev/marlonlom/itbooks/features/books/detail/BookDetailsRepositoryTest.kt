/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import dev.marlonlom.itbooks.core.database.LocalDataSource
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class BookDetailsRepositoryTest {

  private lateinit var repository: BookDetailsRepository

  @Test
  fun `Should return null`() = runBlocking {
    val datasource = object : LocalDataSource {
      override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(emptyList())
      override suspend fun addNewBooks(books: List<NewBookEntity>) {}
      override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf(null)
      override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
    }
    repository = BookDetailsRepository(datasource)
    repository.find("9782190866123").collect { item ->
      assertNull(item)
    }
  }

  @Test
  fun `Should return found book details`() = runBlocking {
    val datasource = object : LocalDataSource {
      override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(emptyList())
      override suspend fun addNewBooks(books: List<NewBookEntity>) {}
      override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf(
        NewBookDetailEntity(
          title = "Securing DevOps",
          subtitle = "Security in the Cloud",
          authors = "Julien Vehent",
          publisher = "Manning",
          language = "English",
          isbn10 = "1617294136",
          isbn13 = "9781617294136",
          pages = 384,
          year = 2018,
          rating = 4,
          detail = "An application running in the cloud can benefit from incredible efficiencies, " +
            "but they come with unique security threats too. A DevOps team&#039;s highest priority " +
            "is understanding those risks and hardening the system against them.Securing DevOps " +
            "teaches you the essential techniques to secure your c...",
          price = "$39.65",
          picture = "https://itbook.store/img/books/9781617294136.png",
        ),
      )

      override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
    }
    repository = BookDetailsRepository(datasource)
    repository.find("9781617294136").collect { item ->
      assertNotNull(item)
      if (item != null) {
        assertEquals("Securing DevOps", item.title)
        assertEquals("Security in the Cloud", item.subtitle)
        assertEquals("Julien Vehent", item.authors)
        assertEquals("Manning", item.publisher)
        assertEquals("English", item.language)
        assertEquals("1617294136", item.isbn10)
        assertEquals("9781617294136", item.isbn13)
        assertEquals(384, item.pages)
        assertEquals(2018, item.year)
        assertEquals(4, item.rating)
        assertTrue(item.picture.endsWith("9781617294136.png"))
        assertFalse(item.isFree)
      }
    }
  }
}
