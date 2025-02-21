/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/**
 * Books list screen composable.
 *
 * @author marlonlom
 *
 * @param settingsIconClicked Action for settings icon clicked.
 * @param modifier The modifier for this composable.
 * @param viewModel Books listing Viewmodel dependency.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BooksListScreen(
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
      .background(MaterialTheme.colorScheme.surface),
  ) {
    stickyHeader {
      BooksListHeading(settingsIconClicked = settingsIconClicked)
    }

    when (uiState) {
      NewBooksListUiState.Empty -> {
        item {
          Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalDivider(
              modifier = Modifier.padding(bottom = 5.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
            )
            Text(
              modifier = Modifier.fillMaxWidth(),
              textAlign = TextAlign.Center,
              fontWeight = FontWeight.Bold,
              text = "No books to show :(",
            )
          }
        }
      }

      NewBooksListUiState.Loading -> {
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

      is NewBooksListUiState.Success -> {
        val books = (uiState as NewBooksListUiState.Success).books
        items(
          count = books.size,
          key = { index -> books[index].isbn13 },
        ) { pos ->
          BooksListRow(
            book = books[pos],
            onBookListItemClicked = { isbn13 ->
              Log.d("BooksListScreen", "Book list row clicked: $isbn13")
            },
          )
        }
      }
    }
  }
}
