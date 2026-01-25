/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.itbooks.plugins

import com.android.build.api.dsl.LibraryExtension
import dev.marlonlom.itbooks.configs.Config
import dev.marlonlom.itbooks.extensions.configureAndroidKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

/**
 * Android library convention plugin class.
 * @author marlonlom
 */
class MobileLibConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.library")
      }
      extensions.configure<LibraryExtension> {
        configureAndroidKotlin(this)
        testOptions.apply {
          targetSdk = Config.android.targetSdkVersion
        }
        defaultConfig.apply {
          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
      }
      tasks.withType<Test> {
        jvmArgs("-XX:+EnableDynamicAgentLoading")
      }
    }
  }
}

