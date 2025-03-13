/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.R
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem

/**
 * Book details item grid text composable.
 *
 * @author marlonlom
 *
 * @param headerText Action for getting header text.
 * @param innerLabel Action for getting inner label text.
 * @param modifier The modifier for this composable.
 * @param headerTextStyle Header text style for this composable.
 */
@Composable
private fun BookDetailsItemGridText(
  headerText: () -> String,
  innerLabel: () -> String,
  modifier: Modifier = Modifier,
  headerTextStyle: TextStyle = MaterialTheme.typography.headlineLarge,
) {
  buildAnnotatedString {
    withStyle(
      SpanStyle(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontSize = headerTextStyle.fontSize,
        fontStyle = headerTextStyle.fontStyle,
        fontFamily = headerTextStyle.fontFamily,
      ),
    ) {
      append(headerText())
    }
    appendLine()
    withStyle(
      SpanStyle(
        color = MaterialTheme.colorScheme.onSurface,
      ),
    ) {
      append(innerLabel())
    }
  }.also {
    Text(
      modifier = modifier.padding(top = 2.dp, bottom = 10.dp),
      text = it,
      textAlign = TextAlign.Center,
    )
  }
}

/**
 * Book details item language grid text composable
 *
 * @author marlonlom
 *
 * @param detailsItem Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemLanguageText(detailsItem: BookDetailsItem, modifier: Modifier = Modifier) =
  BookDetailsItemGridText(
    headerText = { "\uD83C\uDF0E" },
    innerLabel = { detailsItem.language },
    modifier = modifier,
  )

/**
 * Book details item pages count grid text composable
 *
 * @author marlonlom
 *
 * @param detailsItem Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemPagesText(detailsItem: BookDetailsItem, modifier: Modifier = Modifier) =
  BookDetailsItemGridText(
    headerText = { "\uD83D\uDCD6" },
    innerLabel = { "${detailsItem.pages} pages" },
    modifier = modifier,
  )

/**
 * Book details item price grid text composable
 *
 * @author marlonlom
 *
 * @param detailsItem Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemPriceText(detailsItem: BookDetailsItem, modifier: Modifier = Modifier) {
  val context = LocalContext.current
  BookDetailsItemGridText(
    headerText = { "\uD83D\uDCB5" },
    innerLabel = {
      when {
        detailsItem.isFree -> context.getString(R.string.text_free)
        else -> detailsItem.price
      }
    },
    modifier = modifier,
  )
}

/**
 * Book details item publish year grid text composable
 *
 * @author marlonlom
 *
 * @param detailsItem Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemYearText(detailsItem: BookDetailsItem, modifier: Modifier = Modifier) =
  BookDetailsItemGridText(
    headerText = { "\uD83D\uDDD3" },
    innerLabel = { "${detailsItem.year}" },
    modifier = modifier,
  )

/**
 * Book details item rating grid text composable
 *
 * @author marlonlom
 *
 * @param detailsItem Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemRatingText(detailsItem: BookDetailsItem, modifier: Modifier = Modifier) {
  val ctx = LocalContext.current
  BookDetailsItemGridText(
    headerText = {
      StringBuilder().also {
        for (num in 1 until 6) {
          it.append(if (num > detailsItem.rating) "☆" else "★")
        }
      }.toString()
    },
    innerLabel = { ctx.getString(R.string.text_rating) },
    headerTextStyle = MaterialTheme.typography.headlineSmall,
    modifier = modifier,
  )
}
