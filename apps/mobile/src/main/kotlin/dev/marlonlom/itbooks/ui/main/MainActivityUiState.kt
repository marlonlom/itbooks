/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

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
   */
  data object Success : MainActivityUiState
}
