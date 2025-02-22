/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.detail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.itbooks.core.database.ItBooksDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class NewBookDetailDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: ItBooksDatabase
  private lateinit var dao: NewBookDetailDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, ItBooksDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.newBookDetailDao()
  }

  @After
  fun teardown() {
    database.close()
  }

  @Test
  fun shouldInsertBookDetailsAndNotFindByIsbn131() = runBlocking {
    val books = newBookDetailEntities(2..4)
    dao.upsertBooks(books)
    val detail = dao.findByIsbn13("131").first()
    assertThat(detail).isNull()
  }

  @Test
  fun shouldInsertBookDetailAndFindByIsbn131() = runBlocking {
    val books = newBookDetailEntities(1..4)
    dao.upsertBooks(books)
    val detail = dao.findByIsbn13("131").first()
    assertThat(detail).isNotNull()
    assertThat(detail).isEqualTo(books[0])
  }

  private fun newBookDetailEntities(intRange: IntRange) = intRange.map {
    NewBookDetailEntity(
      isbn13 = "13$it",
      title = "Book $it",
      price = "$ 15.00",
      picture = "http://www.notengo.com/photo$it.jpg",
      isbn10 = "10$it",
      subtitle = "Lorem ipsum",
      detail = "Lorem ipsum",
      authors = "Lorem ipsum",
      publisher = "Lorem ipsum",
      pages = 365,
      year = 2025,
      rating = 4,
      language = "English",
    )
  }
}
