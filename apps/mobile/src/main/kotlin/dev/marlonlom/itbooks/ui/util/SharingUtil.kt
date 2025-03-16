/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.util

import android.content.Context
import android.content.Intent
import dev.marlonlom.itbooks.R

/**
 * Message sharing single object utility.
 *
 * @author marlonlom
 */
object SharingUtil {

  /**
   * Perform sharing feature for selected message.
   *
   * @param context Application context.
   * @param message MMessage to be shared.
   *
   */
  fun doShare(context: Context, message: String) {
    val chooser = Intent.createChooser(
      Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
        putExtra(Intent.EXTRA_TEXT, message)
      },
      context.getString(R.string.app_name),
    )
    context.startActivity(chooser)
  }
}
