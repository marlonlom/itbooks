/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
plugins {
  id("itbooks.android.application.mobile")
  id("itbooks.android.app.compose.mobile")
  id("itbooks.android.coil")
  id("itbooks.android.koin")
  id("itbooks.spotless")
  alias(libs.plugins.kotlin.parcelize)
}

dependencies {

  implementation(project(":features:core:database"))
  implementation(project(":features:core:preferences"))
  implementation(project(":features:mobile:designsystem"))

  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.bundles.androidx.room)
  implementation(libs.bundles.androidx.m3.adaptive)
}
