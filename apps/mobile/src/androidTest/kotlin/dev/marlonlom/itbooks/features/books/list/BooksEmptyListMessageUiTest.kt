/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksEmptyListMessageUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldClickHeadingSettingsButton() {
    with(composeTestRule) {
      setContent {
        BooksEmptyListMessage()
      }
      onNodeWithContentDescription("Image for books empty list").isDisplayed()
      onNodeWithText(
        text = "Oops! We couldn\'t find any books here.",
        substring = true,
      ).isDisplayed()
      onNodeWithText(
        text = "We\'re adding new books to keep you updated.",
        substring = true,
      ).isDisplayed()
    }
  }
}
