/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.marlonlom.itbooks.core.database.books.BooksInsertionsCallback
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailDao
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookDao
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import java.util.concurrent.Executors

/**
 * ItBooks Room database class.
 *
 * @author marlonlom
 */
@Database(
  entities = [
    NewBookEntity::class,
    NewBookDetailEntity::class,
  ],
  version = 1,
  exportSchema = true,
)
abstract class ItBooksDatabase : RoomDatabase() {

  /**
   * Returns new books dao instance.
   *
   * @return new books dao.
   */
  abstract fun newBookDao(): NewBookDao

  /**
   * Returns new book details dao instance.
   *
   * @return new book details dao.
   */
  abstract fun newBookDetailDao(): NewBookDetailDao

  companion object {

    @Volatile
    private var instance: ItBooksDatabase? = null

    private const val DATABASE_NAME = "itbooks-db"

    private val runIoThread: (() -> Unit) -> Unit = { f ->
      Executors.newSingleThreadExecutor().execute(f)
    }

    /**
     * Returns an instance of ItBooks database.
     *
     * @param context Application context.
     *
     * @return Database instance.
     */
    fun getInstance(context: Context): ItBooksDatabase = instance ?: synchronized(this) {
      instance ?: buildDatabase(context).also { instance = it }
    }

    /**
     * Builds an instance of ItBooks Room database.
     *
     * @param context Application context.
     *
     * @return Database instance.
     */
    private fun buildDatabase(context: Context): ItBooksDatabase = Room.databaseBuilder(
      context = context,
      klass = ItBooksDatabase::class.java,
      name = DATABASE_NAME,
    ).fallbackToDestructiveMigration(false)
      .addCallback(
        BooksInsertionsCallback(
          assetManager = context.assets,
          runIoThread = runIoThread,
        ),
      )
      .build()
  }
}
