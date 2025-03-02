/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.R

/**
 * Books empty list composable ui.
 *
 * @author marlonlom
 */
@Composable
internal fun BooksEmptyListMessage() = Column(modifier = Modifier.fillMaxSize()) {
  HorizontalDivider(
    modifier = Modifier.padding(bottom = 40.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
  )
  Column(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.25f), MaterialTheme.shapes.large)
      .padding(vertical = 20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      modifier = Modifier.padding(vertical = 20.dp),
      painter = painterResource(id = R.drawable.img_books_empty_list),
      contentDescription = stringResource(id = R.string.text_books_empty_list_cd),
    )

    val annotatedString = buildAnnotatedString {
      withStyle(
        style = MaterialTheme.typography.titleLarge.toSpanStyle(),
      ) {
        append(stringResource(R.string.text_books_list_empty_title01))
      }
      append("\n")
      append(stringResource(R.string.text_books_list_empty_title02))
    }

    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(top = 20.dp),
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onSurface,
      text = annotatedString,
    )
  }
}
