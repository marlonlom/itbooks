/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.scaffold

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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

  val detailBgModifier = when (navigator.scaffoldValue.secondary) {
    PaneAdaptedValue.Hidden -> Modifier.background(MaterialTheme.colorScheme.surface)
    else -> Modifier.background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.large)
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
            Log.d("AppScaffold", "Book list item [$isbn13] Selected.")
            navigator.navigateTo(
              pane = ListDetailPaneScaffoldRole.Detail,
              content = ItBookNavigationItem(isbn13),
            )
          },
        )
      }
    },
    detailPane = {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .then(detailBgModifier),
        contentAlignment = Alignment.Center,
      ) {
        navigator.currentDestination?.content?.let { item ->
          Text(
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            text = "Details for $item",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }
    },
  )
}
