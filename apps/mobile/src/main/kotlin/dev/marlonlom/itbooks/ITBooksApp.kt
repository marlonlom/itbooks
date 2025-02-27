/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dev.marlonlom.itbooks.di.appKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * ITBooks Application class.
 *
 * @author marlonlom
 */
class ITBooksApp :
  Application(),
  ImageLoaderFactory {

  override fun onCreate() {
    super.onCreate()
    this.setupKoin()
  }

  private fun setupKoin() {
    startKoin {
      androidContext(this@ITBooksApp)
      androidLogger(Level.DEBUG)
      modules(appKoinModule)
    }
  }

  override fun newImageLoader(): ImageLoader = ImageLoader(this)
    .newBuilder()
    .memoryCachePolicy(CachePolicy.ENABLED)
    .memoryCache {
      MemoryCache.Builder(this)
        .maxSizePercent(0.1)
        .strongReferencesEnabled(true)
        .build()
    }
    .diskCachePolicy(CachePolicy.ENABLED)
    .diskCache {
      DiskCache.Builder()
        .maxSizePercent(0.03)
        .directory(cacheDir)
        .build()
    }
    .logger(DebugLogger())
    .build()
}
