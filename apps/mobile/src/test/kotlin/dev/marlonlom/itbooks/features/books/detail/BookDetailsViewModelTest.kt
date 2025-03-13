/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import androidx.lifecycle.SavedStateHandle
import dev.marlonlom.itbooks.core.database.LocalDataSource
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import dev.marlonlom.itbooks.ui.scaffold.ItBookNavigationItem
import dev.marlonlom.itbooks.util.MainDispatcherRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class BookDetailsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: BookDetailsViewModel

  @Test
  fun `Should return book detail not found as ui state`() = runTest {
    val datasource = object : LocalDataSource {
      override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(emptyList())
      override suspend fun addNewBooks(books: List<NewBookEntity>) {}
      override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf(null)
      override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
    }
    viewModel = BookDetailsViewModel(SavedStateHandle(), BookDetailsRepository(datasource))
    viewModel.find(ItBookNavigationItem("9781800562738"))
    this.backgroundScope.launch {
      viewModel.uiState.collectLatest { uiState ->
        assertNotNull(uiState)
        assertTrue(uiState == BookDetailsUiState.None)
      }
    }
  }

  @Test
  fun `Should return found book details as ui state`() = runTest {
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
    viewModel = BookDetailsViewModel(SavedStateHandle(), BookDetailsRepository(datasource))
    viewModel.find(ItBookNavigationItem("9781617294136"))
    this.backgroundScope.launch {
      viewModel.uiState.collectLatest { uiState ->
        assertNotNull(uiState)
        when (uiState) {
          is BookDetailsUiState.Success -> {
            assertNotNull(uiState.data)
            assertEquals("Securing DevOps", uiState.data.title)
            assertEquals("Security in the Cloud", uiState.data.subtitle)
            assertEquals("Julien Vehent", uiState.data.authors)
            assertEquals("Manning", uiState.data.publisher)
            assertEquals("English", uiState.data.language)
            assertEquals("1617294136", uiState.data.isbn10)
            assertEquals("9781617294136", uiState.data.isbn13)
            assertEquals(384, uiState.data.pages)
            assertEquals(2018, uiState.data.year)
            assertEquals(4, uiState.data.rating)
            assertTrue(uiState.data.picture.endsWith("9781617294136.png"))
            assertFalse(uiState.data.isFree)
          }

          else -> {
            fail()
          }
        }
      }
    }
  }
}
