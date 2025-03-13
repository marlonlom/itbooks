/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.features.books.detail.BookDetailsUiState
import dev.marlonlom.itbooks.features.books.detail.slots.BookDetailsDescriptionSlot
import dev.marlonlom.itbooks.features.books.detail.slots.BookDetailsHeadingSlot
import dev.marlonlom.itbooks.features.books.detail.slots.BookDetailsOverviewSlot
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsHeader

/**
 * IT Book details normal content screen composable ui.
 *
 * @author marlonlom
 *
 * @param uiStateProvider Action that returns book details ui state.
 * @param onBack Action for back navigation.
 * @param onBuy Action for buying a book by its isbn13.
 * @param onShare Action for sharing a book by its isbn13.
 * @param onReadMore Action for read more link clicked.
 * @param backgroundColor Content background color.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BookDetailsNormalContent(
  uiStateProvider: () -> BookDetailsUiState,
  onBack: () -> Unit,
  onBuy: (String) -> Unit,
  onShare: (String) -> Unit,
  onReadMore: (String) -> Unit,
  backgroundColor: Color = MaterialTheme.colorScheme.background,
) {
  val uiStateProvided = uiStateProvider()
  LazyColumn(
    modifier = Modifier
      .fillMaxWidth()
      .background(backgroundColor),
  ) {
    stickyHeader {
      BookDetailsHeader(
        bookIsbn13 = {
          when (uiStateProvided) {
            is BookDetailsUiState.Success -> uiStateProvided.data.isbn13
            else -> ""
          }
        },
        onBack = onBack,
        onBuy = onBuy,
        onShare = onShare,
        rowBackground = backgroundColor,
      )
    }

    when (uiStateProvided) {
      BookDetailsUiState.Loading -> {
        item {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .background(backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            HorizontalDivider(
              modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
            )
            CircularProgressIndicator(
              modifier = Modifier
                .padding(20.dp)
                .testTag("book_detail_loading_indicator"),
            )
          }
        }
      }

      BookDetailsUiState.None -> {
        item {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(backgroundColor),
            contentAlignment = Alignment.Center,
          ) {
            Text(
              text = "No book, please select a book!",
              style = MaterialTheme.typography.bodyMedium,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onBackground,
              maxLines = 2,
            )
          }
        }
      }

      is BookDetailsUiState.Success -> {
        uiStateProvided.data.apply {
          item {
            BookDetailsHeadingSlot(item = this@apply)
            BookDetailsOverviewSlot(item = this@apply)
            BookDetailsDescriptionSlot(item = this@apply, onReadMore = onReadMore)
            Spacer(modifier = Modifier.height(48.dp))
          }
        }
      }
    }
  }
}
