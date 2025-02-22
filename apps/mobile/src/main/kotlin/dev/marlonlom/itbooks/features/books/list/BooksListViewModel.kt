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
 * Books list viewmodel class.
 *
 * @author marlonlom
 *
 * @property repository Books list repository.
 */
class BooksListViewModel(private val repository: BooksListRepository) : ViewModel() {

  private val _uiState: MutableStateFlow<BooksListUiState> = MutableStateFlow(BooksListUiState.Empty)
  val uiState: StateFlow<BooksListUiState> = _uiState
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = BooksListUiState.Loading,
    )

  fun fetchBooksList() = viewModelScope.launch {
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
