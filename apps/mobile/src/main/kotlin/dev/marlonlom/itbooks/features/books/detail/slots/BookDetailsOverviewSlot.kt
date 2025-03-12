/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemAuthorsText
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemLanguageText
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemPagesText
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemPriceText
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemPublisherText
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemRatingText
import dev.marlonlom.itbooks.features.books.detail.widgets.BookDetailsItemYearText

/**
 * Book details overview slot composable.
 *
 * @author marlonlom
 *
 * @param item Book detail.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun BookDetailsOverviewSlot(item: BookDetailsItem) {
  val maxItemsInEachRow = 3

  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 20.dp),
    text = "Overview",
    color = MaterialTheme.colorScheme.primary,
    style = MaterialTheme.typography.titleMedium,
    fontWeight = FontWeight.Bold,
  )

  FlowRow(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 10.dp, bottom = 20.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    maxItemsInEachRow = maxItemsInEachRow,
  ) {
    val gridModifier = Modifier.weight(100.0f / maxItemsInEachRow)
    BookDetailsItemRatingText(item, gridModifier)
    BookDetailsItemPagesText(item, gridModifier)
    BookDetailsItemPriceText(item, gridModifier)
    BookDetailsItemYearText(item, gridModifier)
    BookDetailsItemLanguageText(item, gridModifier)
    Spacer(gridModifier)
    BookDetailsItemAuthorsText(item, modifier = Modifier.fillMaxWidth())
    BookDetailsItemPublisherText(item, modifier = Modifier.fillMaxWidth())
  }
}
