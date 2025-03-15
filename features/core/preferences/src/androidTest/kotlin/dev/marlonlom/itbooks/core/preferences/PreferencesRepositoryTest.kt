/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.preferences

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class PreferencesRepositoryTest {

  private val testCoroutineDispatcher = StandardTestDispatcher()
  private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())

  private var repository: PreferencesRepository? = null

  @Before
  fun setUp() {
    repository = PreferencesRepository(
      TestablePreferencesDataStore.getInstance(testCoroutineScope),
    )
  }

  @After
  fun tearDown() {
    testCoroutineScope.cancel()
    repository = null
  }

  @Test
  fun repository_testFetchInitialPreferences() {
    testCoroutineScope.launch {
      repository!!.settingsFlow.collectLatest { preferences ->
        assertThat(preferences.useDarkTheme).isEqualTo(false)
        assertThat(preferences.useDynamicColors).isEqualTo(true)
        assertThat(preferences.colorContrast).isEqualTo(UserColorContrasts.STANDARD.name)
      }
    }
  }

  @Test
  fun repository_testUpdateStringPreference() {
    testCoroutineScope.launch {
      with(repository!!) {
        this.updateStringPreference("color_contrast", UserColorContrasts.MEDIUM.name)
        settingsFlow.collectLatest { preferences ->
          assertThat(preferences.useDarkTheme).isEqualTo(true)
          assertThat(preferences.useDynamicColors).isEqualTo(false)
          assertThat(preferences.colorContrast).isEqualTo(UserColorContrasts.MEDIUM.name)
        }
      }
    }
  }

  @Test
  fun repository_testUpdateBooleanPreferences() {
    testCoroutineScope.launch {
      with(repository!!) {
        this.updateBooleanPreference("dark_theme", true)
        this.updateBooleanPreference("dynamic_colors", false)
        settingsFlow.collectLatest { preferences ->
          assertThat(preferences.useDarkTheme).isEqualTo(true)
          assertThat(preferences.useDynamicColors).isEqualTo(false)
          assertThat(preferences.colorContrast).isEqualTo(UserColorContrasts.STANDARD.name)
        }
      }
    }
  }
}
