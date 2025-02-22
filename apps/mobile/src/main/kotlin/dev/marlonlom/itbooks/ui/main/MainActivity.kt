/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.marlonlom.itbooks.ui.scaffold.AppScaffold
import dev.marlonlom.itbooks.ui.theme.ITBooksTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

/**
 * Mobile app main activity class.
 *
 * @author marlonlom
 */
class MainActivity : ComponentActivity() {

  /** Main activity viewmodel */
  private val mainActivityViewModel: MainActivityViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ITBooksTheme {
        KoinContext {
          AppScaffold()
        }
      }
    }
  }
}
