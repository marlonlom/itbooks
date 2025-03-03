/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/**
 * Books list screen composable.
 *
 * @author marlonlom
 *
 * @param onBookListItemClicked Action for book item clicked.
 * @param settingsIconClicked Action for settings icon clicked.
 * @param modifier The modifier for this composable.
 * @param viewModel Books listing Viewmodel dependency.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BooksListScreen(
  onBookListItemClicked: (String) -> Unit,
  settingsIconClicked: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: BooksListViewModel = koinViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle(
    lifecycleOwner = LocalLifecycleOwner.current,
  )

  LaunchedEffect(key1 = viewModel, block = { viewModel.fetchBooksList() })

  LazyColumn(
    modifier = modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.surface),
  ) {
    stickyHeader {
      BooksListHeading(settingsIconClicked = settingsIconClicked)
    }

    when (uiState) {
      BooksListUiState.Empty -> {
        item {
          BooksEmptyListMessage()
        }
      }

      BooksListUiState.Loading -> {
        item {
          Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            HorizontalDivider(
              modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
            )
            CircularProgressIndicator(modifier = Modifier.padding(20.dp))
          }
        }
      }

      is BooksListUiState.Success -> {
        val books = (uiState as BooksListUiState.Success).books
        items(
          count = books.size,
          key = { index -> books[index].isbn13 },
        ) { pos ->
          BooksListRow(
            book = books[pos],
            onBookListItemClicked = { isbn13 ->
              onBookListItemClicked(isbn13)
            },
          )
        }
      }
    }
  }
}
