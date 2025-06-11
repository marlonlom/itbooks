/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.itbooks.plugins

import dev.marlonlom.itbooks.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Android coil convention plugin class.
 * @author marlonlom
 */
class AndroidCoilConventionPlugin: Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      dependencies {
        add("implementation", versionCatalog().findLibrary("coil-compose").get())
        add("androidTestImplementation", versionCatalog().findLibrary("coil-test").get())
      }
    }
  }
}

