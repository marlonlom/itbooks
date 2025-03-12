/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import androidx.compose.runtime.Immutable

/**
 * IT Book details ui state interface definition.
 *
 * @author marlonlom
 */
@Immutable
sealed interface BookDetailsUiState {

  /**
   * IT Book details ui state object: None.
   *
   * @author marlonlom
   */
  data object None : BookDetailsUiState

  /**
   * IT Book details ui state object: Loading.
   *
   * @author marlonlom
   */
  data object Loading : BookDetailsUiState

  /**
   * IT Book details ui state object: Success.
   *
   * @author marlonlom
   *
   * @property data Book details data.
   */
  data class Success(val data: BookDetailsItem) : BookDetailsUiState
}
