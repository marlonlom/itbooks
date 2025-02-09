/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Branded color contrasts enum class.
 *
 * @author marlonlom
 *
 * @property light Light color scheme.
 * @property dark Dark color scheme.
 *
 */
enum class ITBooksColorContrasts(val light: ColorScheme, val dark: ColorScheme) {

  /** Default branded color contrast: Standard. */
  STANDARD(
    light = lightColorScheme(
      primary = Color(0xFF1A6585),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFFC2E8FF),
      onPrimaryContainer = Color(0xFF004D68),
      secondary = Color(0xFF2D628B),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFFCDE5FF),
      onSecondaryContainer = Color(0xFF0A4A72),
      tertiary = Color(0xFF456731),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFFC6EEAA),
      onTertiaryContainer = Color(0xFF2E4F1C),
      error = Color(0xFFBA1A1A),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFFFFDAD6),
      onErrorContainer = Color(0xFF93000A),
      background = Color(0xFFF6FAFE),
      onBackground = Color(0xFF171C1F),
      surface = Color(0xFFF6FAFE),
      onSurface = Color(0xFF171C1F),
      surfaceVariant = Color(0xFFDCE3E9),
      onSurfaceVariant = Color(0xFF40484D),
      outline = Color(0xFF71787D),
      outlineVariant = Color(0xFFC0C7CD),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF2C3134),
      inverseOnSurface = Color(0xFFEDF1F5),
      inversePrimary = Color(0xFF8ECFF2),
      surfaceDim = Color(0xFFD6DADE),
      surfaceBright = Color(0xFFF6FAFE),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFF0F4F8),
      surfaceContainer = Color(0xFFEAEEF2),
      surfaceContainerHigh = Color(0xFFE5E9ED),
      surfaceContainerHighest = Color(0xFFDFE3E7),
    ),
    dark = darkColorScheme(
      primary = Color(0xFF8ECFF2),
      onPrimary = Color(0xFF003548),
      primaryContainer = Color(0xFF004D68),
      onPrimaryContainer = Color(0xFFC2E8FF),
      secondary = Color(0xFF9ACBFA),
      onSecondary = Color(0xFF003352),
      secondaryContainer = Color(0xFF0A4A72),
      onSecondaryContainer = Color(0xFFCDE5FF),
      tertiary = Color(0xFFABD290),
      onTertiary = Color(0xFF183706),
      tertiaryContainer = Color(0xFF2E4F1C),
      onTertiaryContainer = Color(0xFFC6EEAA),
      error = Color(0xFFFFB4AB),
      onError = Color(0xFF690005),
      errorContainer = Color(0xFF93000A),
      onErrorContainer = Color(0xFFFFDAD6),
      background = Color(0xFF0F1417),
      onBackground = Color(0xFFDFE3E7),
      surface = Color(0xFF0F1417),
      onSurface = Color(0xFFDFE3E7),
      surfaceVariant = Color(0xFF40484D),
      onSurfaceVariant = Color(0xFFC0C7CD),
      outline = Color(0xFF8A9297),
      outlineVariant = Color(0xFF40484D),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFDFE3E7),
      inverseOnSurface = Color(0xFF2C3134),
      inversePrimary = Color(0xFF1A6585),
      surfaceDim = Color(0xFF0F1417),
      surfaceBright = Color(0xFF353A3D),
      surfaceContainerLowest = Color(0xFF0A0F12),
      surfaceContainerLow = Color(0xFF171C1F),
      surfaceContainer = Color(0xFF1B2023),
      surfaceContainerHigh = Color(0xFF262B2E),
      surfaceContainerHighest = Color(0xFF313539),
    ),
  ),

  /** Branded color contrast: Medium. */
  MEDIUM(
    light = lightColorScheme(
      primary = Color(0xFF003B50),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFF2E7494),
      onPrimaryContainer = Color(0xFFFFFFFF),
      secondary = Color(0xFF00395B),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFF3E719B),
      onSecondaryContainer = Color(0xFFFFFFFF),
      tertiary = Color(0xFF1E3E0C),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFF53763E),
      onTertiaryContainer = Color(0xFFFFFFFF),
      error = Color(0xFF740006),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFFCF2C27),
      onErrorContainer = Color(0xFFFFFFFF),
      background = Color(0xFFF6FAFE),
      onBackground = Color(0xFF171C1F),
      surface = Color(0xFFF6FAFE),
      onSurface = Color(0xFF0D1215),
      surfaceVariant = Color(0xFFDCE3E9),
      onSurfaceVariant = Color(0xFF30373C),
      outline = Color(0xFF4C5358),
      outlineVariant = Color(0xFF676E73),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF2C3134),
      inverseOnSurface = Color(0xFFEDF1F5),
      inversePrimary = Color(0xFF8ECFF2),
      surfaceDim = Color(0xFFC3C7CB),
      surfaceBright = Color(0xFFF6FAFE),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFF0F4F8),
      surfaceContainer = Color(0xFFE5E9ED),
      surfaceContainerHigh = Color(0xFFD9DDE1),
      surfaceContainerHighest = Color(0xFFCED2D6),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFB3E3FF),
      onPrimary = Color(0xFF00293A),
      primaryContainer = Color(0xFF5798BA),
      onPrimaryContainer = Color(0xFF000000),
      secondary = Color(0xFFC1E0FF),
      onSecondary = Color(0xFF002841),
      secondaryContainer = Color(0xFF6495C1),
      onSecondaryContainer = Color(0xFF000000),
      tertiary = Color(0xFFC0E8A4),
      onTertiary = Color(0xFF0E2C00),
      tertiaryContainer = Color(0xFF769B5F),
      onTertiaryContainer = Color(0xFF000000),
      error = Color(0xFFFFD2CC),
      onError = Color(0xFF540003),
      errorContainer = Color(0xFFFF5449),
      onErrorContainer = Color(0xFF000000),
      background = Color(0xFF0F1417),
      onBackground = Color(0xFFDFE3E7),
      surface = Color(0xFF0F1417),
      onSurface = Color(0xFFFFFFFF),
      surfaceVariant = Color(0xFF40484D),
      onSurfaceVariant = Color(0xFFD6DDE3),
      outline = Color(0xFFACB3B8),
      outlineVariant = Color(0xFF8A9197),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFDFE3E7),
      inverseOnSurface = Color(0xFF262B2E),
      inversePrimary = Color(0xFF004E69),
      surfaceDim = Color(0xFF0F1417),
      surfaceBright = Color(0xFF404548),
      surfaceContainerLowest = Color(0xFF04080B),
      surfaceContainerLow = Color(0xFF191E21),
      surfaceContainer = Color(0xFF24282C),
      surfaceContainerHigh = Color(0xFF2E3336),
      surfaceContainerHighest = Color(0xFF393E42),
    ),
  ),

  /** Branded color contrast: Medium. */
  HIGH(
    light = lightColorScheme(
      primary = Color(0xFF003043),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFF004F6B),
      onPrimaryContainer = Color(0xFFFFFFFF),
      secondary = Color(0xFF002F4B),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFF0F4D75),
      onSecondaryContainer = Color(0xFFFFFFFF),
      tertiary = Color(0xFF143303),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFF31511E),
      onTertiaryContainer = Color(0xFFFFFFFF),
      error = Color(0xFF600004),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFF98000A),
      onErrorContainer = Color(0xFFFFFFFF),
      background = Color(0xFFF6FAFE),
      onBackground = Color(0xFF171C1F),
      surface = Color(0xFFF6FAFE),
      onSurface = Color(0xFF000000),
      surfaceVariant = Color(0xFFDCE3E9),
      onSurfaceVariant = Color(0xFF000000),
      outline = Color(0xFF262D32),
      outlineVariant = Color(0xFF434A4F),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF2C3134),
      inverseOnSurface = Color(0xFFFFFFFF),
      inversePrimary = Color(0xFF8ECFF2),
      surfaceDim = Color(0xFFB5B9BD),
      surfaceBright = Color(0xFFF6FAFE),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFEDF1F5),
      surfaceContainer = Color(0xFFDFE3E7),
      surfaceContainerHigh = Color(0xFFD1D5D9),
      surfaceContainerHighest = Color(0xFFC3C7CB),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFE1F3FF),
      onPrimary = Color(0xFF000000),
      primaryContainer = Color(0xFF8ACBEE),
      onPrimaryContainer = Color(0xFF000D15),
      secondary = Color(0xFFE6F1FF),
      onSecondary = Color(0xFF000000),
      secondaryContainer = Color(0xFF96C8F6),
      onSecondaryContainer = Color(0xFF000C19),
      tertiary = Color(0xFFD3FCB6),
      onTertiary = Color(0xFF000000),
      tertiaryContainer = Color(0xFFA7CE8C),
      onTertiaryContainer = Color(0xFF030E00),
      error = Color(0xFFFFECE9),
      onError = Color(0xFF000000),
      errorContainer = Color(0xFFFFAEA4),
      onErrorContainer = Color(0xFF220001),
      background = Color(0xFF0F1417),
      onBackground = Color(0xFFDFE3E7),
      surface = Color(0xFF0F1417),
      onSurface = Color(0xFFFFFFFF),
      surfaceVariant = Color(0xFF40484D),
      onSurfaceVariant = Color(0xFFFFFFFF),
      outline = Color(0xFFEAF1F7),
      outlineVariant = Color(0xFFBCC4C9),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFDFE3E7),
      inverseOnSurface = Color(0xFF000000),
      inversePrimary = Color(0xFF004E69),
      surfaceDim = Color(0xFF0F1417),
      surfaceBright = Color(0xFF4C5154),
      surfaceContainerLowest = Color(0xFF000000),
      surfaceContainerLow = Color(0xFF1B2023),
      surfaceContainer = Color(0xFF2C3134),
      surfaceContainerHigh = Color(0xFF373C3F),
      surfaceContainerHighest = Color(0xFF43474B),
    ),
  ),
  ;

  /**
   * Utility functions for enum class.
   *
   * @author marlonlom
   */
  companion object {

    /**
     * Returns the selected color contrast color scheme by name and if dark theme is applied.
     *
     * @param colorContrast Selected color contrast name.
     * @param darkTheme True/False is dark theme is applied.
     * @return Selected color contrast scheme.
     */
    fun findColorScheme(colorContrast: String, darkTheme: Boolean): ColorScheme = valueOf(colorContrast).let {
      if (darkTheme) it.dark else it.light
    }
  }
}

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
