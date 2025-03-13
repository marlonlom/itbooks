/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BooksListRowUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldClickBooksListRow() {
    with(composeTestRule) {
      val book = BooksListItem(
        isbn13 = "9781491954249",
        title = "Designing Across Senses",
        price = "$27.59",
        picture = "https://itbook.store/img/books/9781491954249.png",
      )
      var clicked = false
      var isbn13 = ""
      setContent {
        BooksListRow(
          book = book,
          onBookListItemClicked = { txt ->
            clicked = true
            isbn13 = txt
          },
        )
      }
      onNodeWithText(book.title).assertIsDisplayed()
      onNodeWithText(book.price).assertIsDisplayed()
      onNodeWithContentDescription("Book picture for ${book.title}").assertIsDisplayed()
      onNodeWithTag("book_list_item_row_${book.isbn13}").performClick()
      Truth.assertThat(clicked).isTrue()
      Truth.assertThat(isbn13).isEqualTo(book.isbn13)
    }
  }
}
