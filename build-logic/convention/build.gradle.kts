/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

group = "dev.marlonlom.itbooks.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
  @Suppress("DEPRECATION")
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.compiler.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidCoil") {
      id = "itbooks.android.coil"
      implementationClass = "dev.marlonlom.itbooks.plugins.AndroidCoilConventionPlugin"
    }
    register("androidKoin") {
      id = "itbooks.android.koin"
      implementationClass = "dev.marlonlom.itbooks.plugins.AndroidKoinConventionPlugin"
    }
    register("androidLibCompose") {
      id = "itbooks.android.lib.compose"
      implementationClass = "dev.marlonlom.itbooks.plugins.ComposeLibraryConventionPlugin"
    }
    register("androidLib") {
      id = "itbooks.android.library"
      implementationClass = "dev.marlonlom.itbooks.plugins.MobileLibConventionPlugin"
    }
    register("androidApp") {
      id = "itbooks.android.application.mobile"
      implementationClass = "dev.marlonlom.itbooks.plugins.MobileAppConventionPlugin"
    }
    register("androidAppCompose") {
      id = "itbooks.android.app.compose.mobile"
      implementationClass = "dev.marlonlom.itbooks.plugins.ComposeMobileAppConventionPlugin"
    }
    register("spotless") {
      id = "itbooks.spotless"
      implementationClass = "dev.marlonlom.itbooks.plugins.SpotlessConventionPlugin"
    }
  }
}
