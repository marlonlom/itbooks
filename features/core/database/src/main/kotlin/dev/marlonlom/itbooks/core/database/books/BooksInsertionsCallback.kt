/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books

import android.content.res.AssetManager
import android.util.Log
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Books insertion room database callback class.
 *
 * @author marlonlom
 *
 * @property assetManager Assets manager.
 * @property runIoThread IO thread function.
 */
internal class BooksInsertionsCallback(
  private val assetManager: AssetManager,
  private val runIoThread: (() -> Unit) -> Unit,
) : Callback() {

  override fun onCreate(db: SupportSQLiteDatabase) {
    super.onCreate(db)
    Log.d(javaClass.name, "onCreate")
    runIoThread {
      processSqlFile(db, "db/new_book_list.insert.sql")
      processSqlFile(db, "db/new_book_details.insert.sql")
    }
  }

  private fun processSqlFile(db: SupportSQLiteDatabase, sqlPath: String) {
    Log.d(javaClass.name, "processSqlFile")
    assetManager.open(sqlPath).bufferedReader().lines().filter { it.isNotEmpty() }.forEach {
      db.execSQL(it)
    }
  }
}
