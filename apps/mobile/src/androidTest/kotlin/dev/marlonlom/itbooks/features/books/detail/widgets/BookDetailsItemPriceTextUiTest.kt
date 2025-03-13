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
internal class BookDetailsItemPriceTextUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayNonFreePriceText() {
    with(composeTestRule) {
      setContent {
        BookDetailsItemPriceText(
          detailsItem = BookDetailsItem(
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
      onNodeWithText("\uD83D\uDCB5", true).assertIsDisplayed()
      onNodeWithText("$60.99", true).assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayFreePriceText() {
    with(composeTestRule) {
      setContent {
        BookDetailsItemPriceText(
          detailsItem = BookDetailsItem(
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
          ),
        )
      }
      onNodeWithText("\uD83D\uDCB5", true).assertIsDisplayed()
      onNodeWithText("Free", true).assertIsDisplayed()
    }
  }
}
