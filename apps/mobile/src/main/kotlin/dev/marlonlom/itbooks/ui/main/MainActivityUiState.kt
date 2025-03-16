/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

import dev.marlonlom.itbooks.core.preferences.UserPreferences

/**
 * Main activity ui state data class.
 *
 * @author marlonlom
 *
 */
sealed interface MainActivityUiState {

  /**
   * Main activity ui state: Loading
   *
   * @author marlonlom
   */
  data object Loading : MainActivityUiState

  /**
   * Main activity ui state: Success
   *
   * @author marlonlom
   *
   * @property userPreferences User preferences data.
   */
  data class Success(val userPreferences: UserPreferences) : MainActivityUiState
}
