/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database.books.list

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
internal class NewBookDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: ItBooksDatabase
  private lateinit var dao: NewBookDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, ItBooksDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.newBookDao()
  }

  @After
  fun teardown() {
    database.close()
  }

  @Test
  fun shouldInsertBooksList() = runBlocking {
    val books: List<NewBookEntity> = (1..4).map { num ->
      NewBookEntity(
        isbn13 = "$num",
        title = "Book $num",
        price = "$ 15.00",
        picture = "http://www.notengo.com/photo$num.jpg",
      )
    }

    dao.upsertBooks(books)
    val list = dao.listAll().first()

    assertThat(list).isNotEmpty()
    assertThat(list).isEqualTo(books)
  }
}
