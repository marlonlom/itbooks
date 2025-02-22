/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

/**
 * IT Books list ui state interface definition.
 *
 * @author marlonlom
 */
sealed interface BooksListUiState {

  /**
   * New books list ui state object: Loading.
   *
   * @author marlonlom
   */
  data object Loading : BooksListUiState

  /**
   * New books list ui state object: Empty.
   *
   * @author marlonlom
   */
  data object Empty : BooksListUiState

  /**
   * New books list ui state data class: Loading.
   *
   * @author marlonlom
   *
   * @property books New books list data.
   */
  data class Success(val books: List<BooksListItem>) : BooksListUiState
}
