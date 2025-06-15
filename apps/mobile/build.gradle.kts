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

  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  val koinBom = platform(libs.koin.bom)
  implementation(koinBom)

  implementation(project(":features:core:database"))
  implementation(project(":features:core:preferences"))

  implementation(project(":features:mobile:designsystem"))

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.browser)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.bundles.androidx.room)
  implementation(libs.bundles.androidx.m3.adaptive)
  implementation(libs.bundles.koin)
  implementation(libs.coil.compose)

  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)

  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.ui.test.junit4)
  androidTestImplementation(libs.google.truth)

  debugImplementation(libs.androidx.ui.test.manifest)
  debugImplementation(libs.androidx.ui.tooling)
}
