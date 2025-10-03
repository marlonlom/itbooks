/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.itbooks.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Extension function for configuring android compose module.
 * @author marlonlom
 *
 * @param extension Common extension instance.
 */
internal fun Project.configureAndroidCompose(
  extension: CommonExtension<*, *, *, *, *, *>,
) {
  val versionCatalog = versionCatalog()
  extension.apply {

    buildFeatures.compose = true

    dependencies {
      val composeBom = platform(versionCatalog.findLibrary("androidx-compose-bom").get())
      add("implementation", composeBom)
      add("androidTestImplementation", composeBom)

      add("implementation", versionCatalog.findLibrary("androidx-activity-compose").get())
      add("implementation", versionCatalog.findLibrary("androidx-browser").get())
      add("implementation", versionCatalog.findLibrary("androidx-material3").get())
      add("implementation", versionCatalog.findLibrary("androidx-material-icons-extended").get())
      add("implementation", versionCatalog.findLibrary("androidx-ui").get())
      add("implementation", versionCatalog.findLibrary("androidx-ui-graphics").get())
      add("implementation", versionCatalog.findLibrary("androidx-ui-tooling-preview").get())

      add("androidTestImplementation", versionCatalog.findLibrary("androidx-ui-test-junit4").get())

      add("debugImplementation", versionCatalog.findLibrary("androidx-ui-tooling").get())
      add(
        "debugImplementation", versionCatalog.findLibrary("androidx-ui-test-manifest").get()
      )
    }
  }
}

