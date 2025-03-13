/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.itbooks.features.books.detail.BookDetailsUiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BookDetailsNormalContentUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayEmptyBookDetailContent() {
    with(composeTestRule) {
      setContent {
        BookDetailsNormalContent(
          uiStateProvider = { BookDetailsUiState.None },
          onBack = {},
          onBuy = {},
          onShare = {},
          onReadMore = {},
        )
      }
      onNodeWithText("No book, please select a book!").assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayLoadingBookDetailContent() {
    with(composeTestRule) {
      setContent {
        BookDetailsNormalContent(
          uiStateProvider = { BookDetailsUiState.Loading },
          onBack = {},
          onBuy = {},
          onShare = {},
          onReadMore = {},
        )
      }
      onNodeWithTag("book_detail_loading_indicator").assertIsDisplayed()
    }
  }
}
