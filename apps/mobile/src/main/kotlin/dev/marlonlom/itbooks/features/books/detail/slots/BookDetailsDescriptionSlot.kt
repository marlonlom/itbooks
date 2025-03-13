/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.slots

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.R
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem

/**
 * Book detail item description slot composable.
 *
 * @author marlonlom
 *
 * @param item Book details.
 * @param onReadMore Action for read more link clicked.
 */
@Composable
internal fun BookDetailsDescriptionSlot(item: BookDetailsItem, onReadMore: (String) -> Unit) {
  val context = LocalContext.current
  buildAnnotatedString {
    withStyle(
      SpanStyle(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
      ),
    ) {
      append(stringResource(R.string.text_book_detail_description))
    }
    appendLine()
    withStyle(
      SpanStyle(
        color = MaterialTheme.colorScheme.onSurface,
      ),
    ) {
      append(AnnotatedString.fromHtml(item.detail))
      appendLine()
    }
    withLink(
      LinkAnnotation.Clickable(
        tag = "book_detail_read_more",
        styles = TextLinkStyles(
          SpanStyle(
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
          ),
        ),
        linkInteractionListener = {
          onReadMore(
            context.getString(
              R.string.text_book_detail_url,
              item.isbn13,
            ),
          )
        },
      ),
    ) {
      append(stringResource(R.string.text_read_more))
    }
  }.also {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
      text = it,
      textAlign = TextAlign.Start,
    )
  }
}
