/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * Book list row composable.
 *
 * @author marlonlom
 *
 * @param book New book list data item.
 * @param onBookListItemClicked Action for book item clicked.
 */
@Composable
internal fun BooksListRow(book: BooksListItem, onBookListItemClicked: (String) -> Unit) {
  Column(
    modifier = Modifier
      .padding(vertical = 5.dp)
      .clickable {
        onBookListItemClicked(book.isbn13)
      },
  ) {
    HorizontalDivider(
      modifier = Modifier.padding(bottom = 2.dp),
      color = MaterialTheme.colorScheme.surfaceVariant,
    )
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(book.picture)
          .placeholder(MaterialTheme.colorScheme.primaryContainer.value.toInt())
          .error(MaterialTheme.colorScheme.errorContainer.value.toInt()).crossfade(true).build(),
        contentScale = ContentScale.Crop,
        contentDescription = "Book picture for ${book.title}",
        modifier = Modifier
          .height(140.dp)
          .background(Color.Transparent)
          .aspectRatio(5 / 7f),
      )
      Column(
        horizontalAlignment = Alignment.Start,
      ) {
        Text(
          text = book.title,
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
        )
        Text(
          modifier = Modifier
            .padding(top = 10.dp)
            .background(
              color = MaterialTheme.colorScheme.background,
              shape = MaterialTheme.shapes.small,
            )
            .border(
              width = 1.dp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              shape = MaterialTheme.shapes.small,
            )
            .padding(horizontal = 8.dp),
          text = book.price,
          maxLines = 1,
          color = MaterialTheme.colorScheme.onSurface,
          style = MaterialTheme.typography.labelMedium,
        )
      }
    }
  }
}
