/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Brand theme composable function for ITBooks app.
 *
 * @author marlonlom
 *
 * @param darkTheme True/False if dark theme is applied.
 * @param dynamicColor True/False if dynamic colors are applied.
 * @param colorContrast Selected color contrast, defaults to [ITBooksColorContrasts.STANDARD]
 * @param content Composable ui contents.
 */
@Composable
fun ITBooksTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  colorContrast: String = ITBooksColorContrasts.STANDARD.name,
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    else -> ITBooksColorContrasts.findColorScheme(colorContrast, darkTheme)
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = ITBooksType.appTypography,
    content = content,
  )
}
