/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.google.devtools.ksp)
}

android {
  namespace = "dev.marlonlom.itbooks.core.database"
  compileSdk = 35

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")

    ksp {
      arg("room.schemaLocation", "$projectDir/schemas")
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.majorVersion
  }
}

dependencies {

  val koinBom = platform(libs.koin.bom)
  implementation(koinBom)

  implementation(libs.androidx.core.ktx)
  implementation(libs.bundles.androidx.room)
  implementation(libs.bundles.koin)

  ksp(libs.androidx.room.compiler)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.arch.core.testing)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.google.truth)
}
