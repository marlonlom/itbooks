/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.itbooks.features.books.detail.screens.BookDetailsExpandedContent
import dev.marlonlom.itbooks.features.books.detail.screens.BookDetailsNormalContent
import dev.marlonlom.itbooks.ui.scaffold.ItBookNavigationItem
import org.koin.androidx.compose.koinViewModel

/**
 * IT Book details screen pane composable ui.
 *
 * @author marlonlom
 *
 * @param bookItem Book navigation item.
 * @param listPaneAdaptedValue Book details pane adapted value.
 * @param isBackButtonVisible Action for checking if back navigation button should be visible.
 * @param onBack Action for back navigation.
 * @param onBuy Action for buying a book by its isbn13.
 * @param onShare Action for sharing a book by its isbn13.
 * @param onReadMore Action for read more link clicked.
 * @param viewModel Book details viewmodel dependency.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun BookDetailsScreenPane(
  bookItem: ItBookNavigationItem?,
  listPaneAdaptedValue: PaneAdaptedValue,
  isBackButtonVisible: () -> Boolean,
  onBack: () -> Unit,
  onBuy: (String) -> Unit,
  onShare: (String) -> Unit,
  onReadMore: (String) -> Unit,
  viewModel: BookDetailsViewModel = koinViewModel(),
) {
  viewModel.find(bookItem)
  val uiState by viewModel.uiState.collectAsStateWithLifecycle(
    lifecycleOwner = LocalLifecycleOwner.current,
  )
  when (listPaneAdaptedValue) {
    PaneAdaptedValue.Hidden -> {
      BookDetailsNormalContent(
        uiStateProvider = { uiState },
        isBackButtonVisible = isBackButtonVisible,
        onBack = onBack,
        onBuy = onBuy,
        onShare = onShare,
        onReadMore = onReadMore,
      )
    }

    PaneAdaptedValue.Expanded -> {
      BookDetailsExpandedContent(
        uiStateProvider = { uiState },
        isBackButtonVisible = isBackButtonVisible,
        onBack = onBack,
        onBuy = onBuy,
        onShare = onShare,
        onReadMore = onReadMore,
      )
    }
  }
}
