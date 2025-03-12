package dev.marlonlom.itbooks.features.books.detail.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.itbooks.R
import dev.marlonlom.itbooks.features.books.detail.BookDetailsItem

/**
 * Book details item grid wide text composable.
 *
 * @author marlonlom
 *
 * @param titleText Title text as string resource.
 * @param innerLabel Action for getting inner label text.
 * @param modifier The modifier for this composable.
 */
@Composable
private fun BookDetailsItemGridWideText(
  @StringRes titleText: Int,
  innerLabel: () -> String,
  modifier: Modifier = Modifier
) {
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
      append(stringResource(titleText))
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
      modifier = modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
      text = it,
      textAlign = TextAlign.Start,
    )
  }
}

/**
 * Book details item authors grid wide text composable
 *
 * @author marlonlom
 *
 * @param item Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemAuthorsText(item: BookDetailsItem, modifier: Modifier = Modifier) =
  BookDetailsItemGridWideText(
    titleText = R.string.text_authors,
    innerLabel = { item.authors },
    modifier = modifier
  )

/**
 * Book details item publishers grid wide text composable
 *
 * @author marlonlom
 *
 * @param item Book detail.
 * @param modifier The modifier for this composable.
 */
@Composable
internal fun BookDetailsItemPublisherText(item: BookDetailsItem, modifier: Modifier = Modifier) =
  BookDetailsItemGridWideText(
    titleText = R.string.text_publisher,
    innerLabel = { item.publisher },
    modifier = modifier
  )
