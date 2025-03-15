/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Preferences repository class.
 *
 * @author marlonlom
 *
 * @property dataStore Preferences datastore dependency.
 * @property coroutineDispatcher Coroutine dispatcher dependency, defaults to [Dispatchers.IO].
 */
class PreferencesRepository(
  private val dataStore: DataStore<Preferences>,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

  /** Settings as FLow. */
  val settingsFlow: Flow<UserPreferences> = dataStore.data
    .map { preferences -> mapSettings(preferences) }.flowOn(coroutineDispatcher)

  /**
   * Updates boolean preference by defined key.
   *
   * @param key Preference key name.
   * @param newValue New boolean value for save.
   */
  suspend fun updateBooleanPreference(key: String, newValue: Boolean) {
    when (key) {
      PreferenceKeys.DARK_THEME.name -> PreferenceKeys.DARK_THEME
      PreferenceKeys.DYNAMIC_COLORS.name -> PreferenceKeys.DYNAMIC_COLORS
      else -> null
    }.also {
      updatePreference(it, newValue)
    }
  }

  /**
   * Updates string preference by defined key.
   *
   * @param key Preference key name.
   * @param newValue New string value for save.
   */
  suspend fun updateStringPreference(key: String, newValue: String) {
    when (key) {
      PreferenceKeys.COLOR_CONTRAST.name -> PreferenceKeys.COLOR_CONTRAST
      else -> null
    }.also {
      updatePreference(it, newValue)
    }
  }

  /**
   * Updates provided preference.
   *
   * @param T Preference type.
   * @param foundKey Preference key.
   * @param newValue New Preference value.
   */
  private suspend fun <T> updatePreference(foundKey: Preferences.Key<T>?, newValue: T) {
    dataStore.updateData {
      return@updateData it.toMutablePreferences().let { mp ->
        foundKey?.also { k ->
          mp[k] = newValue
        }
        mp
      }.toPreferences()
    }
  }

  /**
   * Map settings to user preference state.
   *
   * @param preferences User preferences from datastore.
   *
   * @return User preferences data.
   */
  private fun mapSettings(preferences: Preferences) = preferences.let {
    UserPreferences(
      useDarkTheme = it[PreferenceKeys.DARK_THEME] ?: false,
      useDynamicColors = it[PreferenceKeys.DYNAMIC_COLORS] ?: true,
      colorContrast = it[PreferenceKeys.COLOR_CONTRAST] ?: UserColorContrasts.STANDARD.name,
    )
  }

  /**
   * Preference keys single object.
   *
   * @author marlonlom
   */
  internal object PreferenceKeys {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
    val DYNAMIC_COLORS = booleanPreferencesKey("dynamic_colors")
    val COLOR_CONTRAST = stringPreferencesKey("color_contrast")
  }
}
