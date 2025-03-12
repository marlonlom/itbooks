/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.slots

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.marlonlom.itbooks.R
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem

@Composable
internal fun BookDetailsHeadingSlot(item: BookDetailsItem) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
  ) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current).data(item.picture)
        .placeholder(MaterialTheme.colorScheme.primaryContainer.value.toInt())
        .error(MaterialTheme.colorScheme.errorContainer.value.toInt()).crossfade(true).build(),
      contentScale = ContentScale.Crop,
      contentDescription = stringResource(R.string.text_book_list_item_picture_cd, item.title),
      modifier = Modifier
        .height(240.dp)
        .background(Color.Transparent)
        .aspectRatio(5 / 7f),
    )
  }

  buildAnnotatedString {
    withStyle(
      SpanStyle(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
      ),
    ) {
      append(AnnotatedString.fromHtml(item.title))
    }

    item.subtitle.also { subtitle ->
      if (subtitle.isNotEmpty()) {
        appendLine()
        withStyle(
          SpanStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
          ),
        ) {
          append(AnnotatedString.fromHtml(subtitle))
        }
      }
    }
  }.also {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp),
      text = it,
      textAlign = TextAlign.Center,
    )
  }
}
