/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.scaffold

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.features.books.detail.BookDetailsScreenPane
import dev.marlonlom.itbooks.features.books.list.BooksListScreen

/**
 * Mobile application scaffold composable.
 *
 * @author marlonlom
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppScaffold() {
  val navigator = rememberListDetailPaneScaffoldNavigator<ItBookNavigationItem>()

  BackHandler(navigator.canNavigateBack()) {
    navigator.navigateBack()
  }

  ListDetailPaneScaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .safeContentPadding(),
    directive = navigator.scaffoldDirective,
    value = navigator.scaffoldValue,
    listPane = {
      AnimatedPane(modifier = Modifier.preferredWidth(300.dp)) {
        BooksListScreen(
          settingsIconClicked = {
            Log.d("AppScaffold", "Settings icon clicked.")
          },
          onBookListItemClicked = { isbn13 ->
            navigator.navigateTo(
              pane = ListDetailPaneScaffoldRole.Detail,
              content = ItBookNavigationItem(isbn13),
            )
          },
        )
      }
    },
    detailPane = {
      AnimatedPane {
        BookDetailsScreenPane(
          bookItem = navigator.currentDestination?.content,
          listPaneAdaptedValue = navigator.scaffoldValue.secondary,
          onBack = {
            if (navigator.canNavigateBack()) {
              navigator.navigateBack()
            }
          },
          onBuy = {
            Log.d("AppScaffold", "Buy book: $it")
          },
          onShare = {
            Log.d("AppScaffold", "Share book: $it")
          },
        )
      }
    },
  )
}
