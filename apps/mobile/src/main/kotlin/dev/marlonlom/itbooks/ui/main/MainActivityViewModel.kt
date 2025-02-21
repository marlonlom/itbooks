/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.itbooks.ui.main.MainActivityUiState.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

/**
 * Main activity viewmodel class.
 *
 * @author marlonlom
 *
 */
class MainActivityViewModel : ViewModel() {

  /** Main viewmodel ui state */
  val uiState: StateFlow<MainActivityUiState> = flowOf(Loading).stateIn(
    scope = viewModelScope,
    initialValue = Loading,
    started = SharingStarted.WhileSubscribed(5_000),
  )
}
