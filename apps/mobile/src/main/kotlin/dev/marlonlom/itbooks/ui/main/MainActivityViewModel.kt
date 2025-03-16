/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.itbooks.core.preferences.PreferencesRepository
import dev.marlonlom.itbooks.ui.main.MainActivityUiState.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Main activity viewmodel class.
 *
 * @author marlonlom
 *
 * @property repository Preferences repository dependency.
 */
class MainActivityViewModel(private val repository: PreferencesRepository) : ViewModel() {

  /** Main viewmodel ui state */
  val uiState: StateFlow<MainActivityUiState> = repository.settingsFlow
    .map { MainActivityUiState.Success(it) }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = Loading,
    )
}
