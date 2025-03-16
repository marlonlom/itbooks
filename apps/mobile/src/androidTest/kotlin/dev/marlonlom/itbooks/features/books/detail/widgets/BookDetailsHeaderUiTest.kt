/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.widgets

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BookDetailsHeaderUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayHeaderWithActionButtonsForSelectedBook() {
    with(composeTestRule) {
      var buyButtonTxt = ""
      var shareButtonTxt = ""
      var backButtonClicked = false
      val bookDetailsItem = BookDetailsItem(
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
      setContent {
        BookDetailsHeader(
          selectedBook = { bookDetailsItem },
          isBackButtonVisible = { true },
          onBack = {
            backButtonClicked = true
          },
          onBuy = {
            buyButtonTxt = it
          },
          onShare = {
            shareButtonTxt = it
          },
        )
      }
      onNodeWithTag("book_detail_buy_btn").assertIsDisplayed()
      onNodeWithTag("book_detail_buy_btn").performClick()
      assertThat(buyButtonTxt).endsWith(bookDetailsItem.isbn13)

      onNodeWithTag("book_detail_share_btn").assertIsDisplayed()
      onNodeWithTag("book_detail_share_btn").performClick()
      assertThat(shareButtonTxt).contains(bookDetailsItem.title)

      onNodeWithTag("book_detail_back_btn").assertIsDisplayed()
      onNodeWithTag("book_detail_back_btn").performClick()
      assertThat(backButtonClicked).isTrue()
    }
  }
}
