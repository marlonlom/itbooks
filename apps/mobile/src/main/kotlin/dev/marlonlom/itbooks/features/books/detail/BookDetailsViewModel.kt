/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.features.books.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.itbooks.ui.scaffold.ItBookNavigationItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

/**
 * IT Book details viewmodel class.
 *
 * @author marlonlom
 *
 * @property savedStateHandle Saved state handler for this viewmodel.
 * @property repository Book details repository dependency.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailsViewModel(
  private val savedStateHandle: SavedStateHandle,
  private val repository: BookDetailsRepository,
) : ViewModel() {

  /** State flow for book details information ui state. */
  val uiState: StateFlow<BookDetailsUiState> = savedStateHandle.getStateFlow(
    key = "book_isbn13",
    initialValue = "",
  ).flatMapLatest { isbn13 ->
    mapToBookDetailsUiState(isbn13)
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = BookDetailsUiState.None,
  )

  /**
   * Search book detailed information for provided isbn13.
   *
   * @param item IT Book navigation item
   */
  fun find(item: ItBookNavigationItem?) {
    savedStateHandle["book_isbn13"] = item?.isbn13 ?: ""
  }

  /**
   * Converts book isbn13 to book details ui state and returns it as a Flow.
   *
   * @param isbn13 IT Book isbn13.
   *
   * @return IT Book details ui state as flow.
   */
  private fun mapToBookDetailsUiState(isbn13: String): Flow<BookDetailsUiState> = flow {
    when {
      isbn13.isEmpty() -> {
        emit(BookDetailsUiState.None)
      }

      else -> {
        emit(BookDetailsUiState.Loading)
        repository.find(isbn13).catch {
          emit(BookDetailsUiState.None)
        }.collect { foundItem ->
          emit(
            if (foundItem != null) {
              BookDetailsUiState.Success(foundItem)
            } else {
              BookDetailsUiState.None
            },
          )
        }
      }
    }
  }
}
