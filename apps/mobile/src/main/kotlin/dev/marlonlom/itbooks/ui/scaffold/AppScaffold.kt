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
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dev.marlonlom.itbooks.features.books.detail.BookDetailsScreenPane
import dev.marlonlom.itbooks.features.books.list.BooksListScreen
import kotlinx.coroutines.launch

/**
 * Mobile application scaffold composable.
 *
 * @author marlonlom
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppScaffold() {
  val navigator = rememberListDetailPaneScaffoldNavigator<ItBookNavigationItem>()
  val coroutineScope = rememberCoroutineScope()

  BackHandler(navigator.canNavigateBack()) {
    coroutineScope.launch {
      navigator.navigateBack()
    }
  }

  ListDetailPaneScaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .safeContentPadding(),
    directive = navigator.scaffoldDirective,
    value = navigator.scaffoldValue,
    listPane = {
      AnimatedPane {
        BooksListScreen(
          settingsIconClicked = {
            Log.d("AppScaffold", "Settings icon clicked.")
          },
          onBookListItemClicked = { isbn13 ->
            coroutineScope.launch {
              navigator.navigateTo(
                pane = ListDetailPaneScaffoldRole.Detail,
                contentKey = ItBookNavigationItem(isbn13),
              )
            }
          },
        )
      }
    },
    detailPane = {
      AnimatedPane {
        BookDetailsScreenPane(
          bookItem = navigator.currentDestination?.contentKey,
          listPaneAdaptedValue = navigator.scaffoldValue.secondary,
          isBackButtonVisible = {
            navigator.scaffoldValue.primary.equals(PaneAdaptedValue.Expanded)
              .and(navigator.scaffoldValue.secondary.equals(PaneAdaptedValue.Hidden))
          },
          onBack = {
            if (navigator.canNavigateBack()) {
              coroutineScope.launch {
                navigator.navigateBack()
              }
            }
          },
          onBuy = {
            Log.d("AppScaffold", "Buy book, url($it)")
          },
          onShare = {
            Log.d("AppScaffold", "Share book, msg($it)")
          },
          onReadMore = {
            Log.d("AppScaffold", "Read more, url($it)")
          },
        )
      }
    },
  )
}
