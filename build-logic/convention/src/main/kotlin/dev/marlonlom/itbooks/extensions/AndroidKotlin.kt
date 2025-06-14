/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.itbooks.extensions

import com.android.build.api.dsl.CommonExtension
import dev.marlonlom.itbooks.configs.AndroidConfig
import dev.marlonlom.itbooks.configs.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Extension function for configuring android kotlin module.
 * @author marlonlom
 *
 * @param extension Common extension instance.
 * @param androidConfig Android configuration information.
 *
 */
internal fun Project.configureAndroidKotlin(
  extension: CommonExtension<*, *, *, *, *, *>,
  androidConfig: AndroidConfig = Config.android,
) {
  with(extension) {
    namespace = androidConfig.nameSpace
    compileSdk = androidConfig.compileSdkVersion

    defaultConfig.apply {
      minSdk = androidConfig.minSdkVersion
      testInstrumentationRunner = androidConfig.testInstrumentationRunner
      vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
      sourceCompatibility = Config.jvm.javaVersion
      targetCompatibility = Config.jvm.javaVersion
    }

    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    dependencies {
      add("implementation", versionCatalog().findLibrary("androidx-core-ktx").get())

      add("testImplementation", versionCatalog().findLibrary("junit").get())
      add("testImplementation", versionCatalog().findLibrary("kotlinx-coroutines-test").get())

      add("androidTestImplementation", versionCatalog().findLibrary("androidx-espresso-core").get())
      add("androidTestImplementation", versionCatalog().findLibrary("androidx-junit").get())
      add("androidTestImplementation", versionCatalog().findLibrary("google-truth").get())
    }
  }
  tasks.withType<KotlinCompile>().configureEach {
    @Suppress("DEPRECATION")
    kotlinOptions {
      jvmTarget = Config.jvm.kotlinJvm
    }
  }
}

