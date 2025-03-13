/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.R

/**
 * Book details compact header composable.
 *
 * @author marlonlom
 *
 * @param bookIsbn13 Action for getting book isbn13.
 * @param onBack Action for back navigation.
 * @param onBuy Action for buying a book by its isbn13.
 * @param onShare Action for sharing a book by its isbn13.
 */
@Composable
internal fun BookDetailsHeader(
  bookIsbn13: () -> String,
  onBack: () -> Unit,
  onBuy: (String) -> Unit,
  onShare: (String) -> Unit,
  rowBackground: Color = MaterialTheme.colorScheme.background,
) {
  val context = LocalContext.current
  val bookIsbn13Found = bookIsbn13()
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(rowBackground)
      .padding(bottom = 4.dp),
  ) {
    IconButton(
      modifier = Modifier.testTag("book_detail_back_btn"),
      onClick = { onBack() },
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        tint = MaterialTheme.colorScheme.secondary,
        contentDescription = null,
      )
    }
    Spacer(Modifier.weight(1f))
    if (bookIsbn13Found.isNotEmpty()) {
      IconButton(
        modifier = Modifier.testTag("book_detail_buy_btn"),
        onClick = {
          onBuy(
            context.getString(
              R.string.text_book_detail_buy_url,
              bookIsbn13Found,
            ),
          )
        },
      ) {
        Icon(
          imageVector = Icons.Rounded.ShoppingCart,
          tint = MaterialTheme.colorScheme.secondary,
          contentDescription = null,
        )
      }
      IconButton(
        modifier = Modifier.testTag("book_detail_share_btn"),
        onClick = { onShare(bookIsbn13Found) },
      ) {
        Icon(
          imageVector = Icons.Rounded.Share,
          tint = MaterialTheme.colorScheme.secondary,
          contentDescription = null,
        )
      }
    }
  }
}
