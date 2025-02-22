/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.ui.scaffold

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * IT Book navigation item parcelable data class.
 *
 * @author marlonlom
 *
 * @property isbn13 Book isbn13.
 */
@Parcelize
data class ItBookNavigationItem(val isbn13: String) : Parcelable
