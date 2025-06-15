/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import dev.marlonlom.itbooks.core.preferences.UserColorContrasts
import dev.marlonlom.itbooks.mobile.designsystem.theme.ITBooksTheme
import dev.marlonlom.itbooks.ui.scaffold.AppScaffold

/**
 * Main app content composable.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState Main activity ui state.
 */
@Composable
internal fun MainContent(mainActivityUiState: MainActivityUiState) = ITBooksTheme(
  darkTheme = shouldUseDarkTheme(mainActivityUiState),
  dynamicColor = shouldUseDynamicColor(mainActivityUiState),
  colorContrast = shouldUseColorContrast(mainActivityUiState),
) {
  AppScaffold()
}

/**
 * Returns true/false if dynamic colors are applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDynamicColor(mainActivityUiState: MainActivityUiState): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> false
  is MainActivityUiState.Success -> mainActivityUiState.userPreferences.useDynamicColors
}

/**
 * Returns true/false if dark theme is applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDarkTheme(mainActivityUiState: MainActivityUiState): Boolean = when (mainActivityUiState) {
  MainActivityUiState.Loading -> isSystemInDarkTheme()
  is MainActivityUiState.Success -> {
    val useDarkTheme = mainActivityUiState.userPreferences.useDarkTheme
    if (useDarkTheme.not()) isSystemInDarkTheme() else useDarkTheme
  }
}

/**
 * Returns the color contrast applied to the ui.
 *
 * @param mainActivityUiState Main activity ui state.
 * @return Color contrast name.
 */
@Composable
private fun shouldUseColorContrast(mainActivityUiState: MainActivityUiState): String = when (mainActivityUiState) {
  MainActivityUiState.Loading -> UserColorContrasts.STANDARD.name
  is MainActivityUiState.Success -> mainActivityUiState.userPreferences.colorContrast
}
