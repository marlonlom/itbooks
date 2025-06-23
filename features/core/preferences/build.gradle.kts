/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
plugins {
  id("itbooks.android.library")
  id("itbooks.android.lib.compose")
  id("itbooks.android.koin")
  id("itbooks.spotless")
}

android {
  namespace = "dev.marlonlom.itbooks.core.preferences"
}

dependencies {
  implementation(libs.androidx.datastore.preferences)
}
