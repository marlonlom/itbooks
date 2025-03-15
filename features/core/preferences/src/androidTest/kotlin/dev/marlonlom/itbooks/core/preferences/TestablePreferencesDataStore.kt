/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.TestScope

/**
 * Testable preferences datastore single object.
 *
 * @author marlonlom
 *
 */
object TestablePreferencesDataStore {

  /**
   * Singleton instance of the testable preferences datastore.
   */
  private var instance: DataStore<Preferences>? = null

  /**
   * Returns a new instance of the testable preferences datastore.
   *
   * @return Testable preferences datastore.
   */
  fun getInstance(testCoroutineScope: TestScope): DataStore<Preferences> = instance ?: synchronized(this) {
    instance ?: PreferenceDataStoreFactory.create(
      scope = testCoroutineScope,
      produceFile = {
        InstrumentationRegistry.getInstrumentation()
          .targetContext.preferencesDataStoreFile(name = "testable-itbooks-settings")
      },
    ).also { instance = it }
  }
}
