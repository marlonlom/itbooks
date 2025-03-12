/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.features.books.detail.BookDetailsUiState

/**
 * IT Book details expanded screen composable ui.
 *
 * @author marlonlom
 *
 * @param uiStateProvider Action that returns book details ui state.
 * @param onBack Action for back navigation.
 * @param onBuy Action for buying a book by its isbn13.
 * @param onShare Action for sharing a book by its isbn13.
 */
@Composable
internal fun BookDetailsExpandedContent(
  uiStateProvider: () -> BookDetailsUiState,
  onBack: () -> Unit,
  onBuy: (String) -> Unit,
  onShare: (String) -> Unit,
) {
  val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
  Box(
    modifier = with(Modifier) {
      fillMaxSize()
      .background(backgroundColor, MaterialTheme.shapes.large)
      .padding(20.dp)
    },
  ) {
    BookDetailsNormalContent(
      uiStateProvider = uiStateProvider,
      onBack = onBack,
      onBuy = onBuy,
      onShare = onShare,
      backgroundColor = backgroundColor,
    )
  }
}
