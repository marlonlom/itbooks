/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import dev.marlonlom.itbooks.core.preferences.PreferencesRepository.PreferenceKeys
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class PreferencesRepositoryTest {

  @OptIn(ExperimentalCoroutinesApi::class)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Test
  fun `settingsFlow should map preferences to UserPreferences`() = runTest {
    val mockDataStore = object : DataStore<Preferences> {
      override val data: Flow<Preferences> = flowOf(defaultTestPreferences)

      override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences =
        transform(defaultTestPreferences)
    }
    val repository = PreferencesRepository(mockDataStore, testDispatcher)
    val userPreferences = repository.settingsFlow.first()
    assertEquals(false, userPreferences.useDarkTheme)
    assertEquals(true, userPreferences.useDynamicColors)
    assertEquals(UserColorContrasts.STANDARD.name, userPreferences.colorContrast)
  }

  @Test
  fun `updateBooleanPreference should update boolean preference`() = runTest {
    var storedPreferences: Preferences = emptyPreferences()
    val mockDataStore = object : DataStore<Preferences> {
      override val data: Flow<Preferences> = flowOf(defaultTestPreferences)

      override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences {
        storedPreferences = transform(storedPreferences)
        return storedPreferences
      }
    }
    val repository = PreferencesRepository(mockDataStore, testDispatcher)
    repository.updateBooleanPreference(PreferenceKeys.DARK_THEME.name, true)
    repository.updateBooleanPreference(PreferenceKeys.DYNAMIC_COLORS.name, false)
    val updatedPreferences = storedPreferences
    assertEquals(true, updatedPreferences[PreferenceKeys.DARK_THEME])
    assertEquals(false, updatedPreferences[PreferenceKeys.DYNAMIC_COLORS])
  }

  @Test
  fun `updateStringPreference should update string preference`() = runTest {
    var storedPreferences: Preferences = emptyPreferences()
    val mockDataStore = object : DataStore<Preferences> {
      override val data: Flow<Preferences> = flowOf(defaultTestPreferences)

      override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences {
        storedPreferences = transform(storedPreferences)
        return storedPreferences
      }
    }
    val repository = PreferencesRepository(mockDataStore, testDispatcher)
    repository.updateStringPreference(PreferenceKeys.COLOR_CONTRAST.name, UserColorContrasts.HIGH.name)
    val updatedPreferences = storedPreferences
    assertEquals(UserColorContrasts.HIGH.name, updatedPreferences[PreferenceKeys.COLOR_CONTRAST])
  }

  private val defaultTestPreferences = emptyPreferences().toMutablePreferences().apply {
    this[PreferenceKeys.DARK_THEME] = false
    this[PreferenceKeys.DYNAMIC_COLORS] = true
    this[PreferenceKeys.COLOR_CONTRAST] = UserColorContrasts.STANDARD.name
  }.toPreferences()
}
