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
      val bookIsbn13 = "9781098111878"
      setContent {
        BookDetailsHeader(
          bookIsbn13 = { bookIsbn13 },
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
      assertThat(buyButtonTxt).endsWith(bookIsbn13)

      onNodeWithTag("book_detail_share_btn").assertIsDisplayed()
      onNodeWithTag("book_detail_share_btn").performClick()
      assertThat(shareButtonTxt).isEqualTo(bookIsbn13)

      onNodeWithTag("book_detail_back_btn").assertIsDisplayed()
      onNodeWithTag("book_detail_back_btn").performClick()
      assertThat(backButtonClicked).isTrue()
    }
  }
}
