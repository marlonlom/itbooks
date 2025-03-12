/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * IT Books list viewmodel class.
 *
 * @author marlonlom
 *
 * @property repository Books list repository.
 */
class BooksListViewModel(private val repository: BooksListRepository) : ViewModel() {

  /** IT books list ui mutable state flow. */
  private val _uiState: MutableStateFlow<BooksListUiState> = MutableStateFlow(BooksListUiState.Empty)

  /** IT books list ui state flow. */
  val uiState: StateFlow<BooksListUiState> = _uiState
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = BooksListUiState.Loading,
    )

  /**
   * Executes the async fetching of the it new books list query.
   */
  fun fetchBooksList() {
    viewModelScope.launch {
      _uiState.update { BooksListUiState.Loading }
      repository.fetchBooks().collect { books ->
        _uiState.update {
          if (books.isEmpty()) {
            BooksListUiState.Empty
          } else {
            BooksListUiState.Success(books)
          }
        }
      }
    }
  }
}
