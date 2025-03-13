/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.widgets

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BookDetailsItemAuthorsTextUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayAuthorsText() {
    with(composeTestRule) {
      setContent {
        BookDetailsItemAuthorsText(
          item = BookDetailsItem(
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
          ),
        )
      }
      onNodeWithText("Kyran Dale", true).assertIsDisplayed()
      onNodeWithText("Authors", true).assertIsDisplayed()
    }
  }
}
