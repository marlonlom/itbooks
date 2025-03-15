/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.preferences

/**
 * User preferences data class.
 *
 * @author marlonlom
 *
 * @property useDarkTheme True/False if using dark theme.
 * @property useDynamicColors True/False if using dynamic colors.
 * @property colorContrast Selected color contrast, defaults to [UserColorContrasts.STANDARD].
 */
data class UserPreferences(
  val useDarkTheme: Boolean,
  val useDynamicColors: Boolean,
  val colorContrast: String = UserColorContrasts.STANDARD.name,
)

/**
 * User color contrasts enum class.
 *
 * @author marlonlom
 */
enum class UserColorContrasts {
  /** User color contrast: Standard. */
  STANDARD,

  /** User color contrast: Medium. */
  MEDIUM,

  /** User color contrast: High. */
  HIGH,
}
