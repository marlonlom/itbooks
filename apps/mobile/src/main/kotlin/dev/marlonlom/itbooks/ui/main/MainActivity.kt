/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.marlonlom.itbooks.features.books.list.BooksListScreen
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
          Scaffold(
            modifier = Modifier
              .fillMaxSize()
              .background(MaterialTheme.colorScheme.background)
              .safeContentPadding(),
          ) { innerPadding ->
            BooksListScreen(
              modifier = Modifier.consumeWindowInsets(innerPadding),
              settingsIconClicked = {
                Log.d(javaClass.simpleName, "Settings icon clicked.")
              },
            )
          }
        }
      }
    }
  }
}
