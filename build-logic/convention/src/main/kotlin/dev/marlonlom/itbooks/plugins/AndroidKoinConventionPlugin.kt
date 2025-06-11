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
 * Android koin convention plugin class.
 * @author marlonlom
 */
class AndroidKoinConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      dependencies {
        val composeBom = platform(versionCatalog().findLibrary("koin-bom").get())
        add("implementation", composeBom)
        add("implementation", versionCatalog().findBundle("koin").get())
      }
    }
  }
}
