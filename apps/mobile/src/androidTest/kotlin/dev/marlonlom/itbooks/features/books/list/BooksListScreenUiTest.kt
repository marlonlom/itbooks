package dev.marlonlom.itbooks.features.books.list

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.itbooks.core.database.LocalDataSource
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class BooksListScreenUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldShowEmptyState() {
    with(composeTestRule) {
      val datasource = object : LocalDataSource {
        override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(emptyList())
        override suspend fun addNewBooks(books: List<NewBookEntity>) {}
        override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf()
        override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
      }
      setContent {
        BooksListScreen(
          onBookListItemClicked = {},
          settingsIconClicked = {},
          viewModel = BooksListViewModel(BooksListRepository(datasource))
        )
      }
      onNodeWithText("IT Books").isDisplayed()
      onNodeWithContentDescription("Brand image for IT Books").isDisplayed()
      onNodeWithTag("heading_settings_btn").isDisplayed()
      onNodeWithText("No books to show :(").isDisplayed()
    }
  }

  @Test
  fun shouldShowEmptyStateAndClickSettingsIcon() {
    with(composeTestRule) {
      val datasource = object : LocalDataSource {
        override fun listNewBooks(): Flow<List<NewBookEntity>> = flowOf(emptyList())
        override suspend fun addNewBooks(books: List<NewBookEntity>) {}
        override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = flowOf()
        override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) {}
      }
      var settingsIconClicked = false
      setContent {
        BooksListScreen(
          onBookListItemClicked = {},
          settingsIconClicked = {
            settingsIconClicked = true
          },
          viewModel = BooksListViewModel(BooksListRepository(datasource))
        )
      }
      onNodeWithText("IT Books").isDisplayed()
      onNodeWithContentDescription("Brand image for IT Books").isDisplayed()
      onNodeWithText("No books to show :(").isDisplayed()
      onNodeWithTag("heading_settings_btn").isDisplayed()
      onNodeWithTag("heading_settings_btn").performClick()
      assertThat(settingsIconClicked).isTrue()
    }
  }

  @Test
  fun shouldShowSuccessState() {
    with(composeTestRule) {
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
      setContent {
        BooksListScreen(
          onBookListItemClicked = {},
          settingsIconClicked = {},
          viewModel = BooksListViewModel(BooksListRepository(datasource))
        )
      }
      onNodeWithText("IT Books").isDisplayed()
      onNodeWithContentDescription("Brand image for IT Books").isDisplayed()
      onNodeWithTag("heading_settings_btn").isDisplayed()
      onNodeWithText("No books to show :(").isNotDisplayed()
      onNodeWithTag("book_list_item_row_9781491954249").isDisplayed()
      onNodeWithTag("book_list_item_row_9781642002263").isDisplayed()
    }
  }

  @Test
  fun shouldShowSuccessStateAndClickBookItem() {
    with(composeTestRule) {
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
      var selectedBook = ""
      setContent {
        BooksListScreen(
          onBookListItemClicked = {
            selectedBook = it
          },
          settingsIconClicked = {},
          viewModel = BooksListViewModel(BooksListRepository(datasource))
        )
      }
      onNodeWithText("IT Books").isDisplayed()
      onNodeWithContentDescription("Brand image for IT Books").isDisplayed()
      onNodeWithTag("heading_settings_btn").isDisplayed()
      onNodeWithText("No books to show :(").isNotDisplayed()
      onNodeWithTag("book_list_item_row_9781491954249").isDisplayed()
      onNodeWithTag("book_list_item_row_9781642002263").isDisplayed()
      onNodeWithTag("book_list_item_row_9781642002263").performClick()
      assertThat(selectedBook).isEqualTo("9781642002263")
    }
  }
}
