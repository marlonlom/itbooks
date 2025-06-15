/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("itbooks.android.library")
  id("itbooks.android.lib.compose")
  id("itbooks.android.koin")
  id("itbooks.spotless")
  alias(libs.plugins.google.devtools.ksp)
}

android {
  namespace = "dev.marlonlom.itbooks.core.database"

  defaultConfig {
    ksp {
      arg("room.schemaLocation", "$projectDir/schemas")
    }
  }
}

dependencies {
  implementation(libs.bundles.androidx.room)

  ksp(libs.androidx.room.compiler)
}
