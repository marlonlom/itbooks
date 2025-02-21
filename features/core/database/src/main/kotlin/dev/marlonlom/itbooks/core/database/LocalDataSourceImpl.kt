/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.itbooks.core.database

import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailDao
import dev.marlonlom.itbooks.core.database.books.detail.NewBookDetailEntity
import dev.marlonlom.itbooks.core.database.books.list.NewBookDao
import dev.marlonlom.itbooks.core.database.books.list.NewBookEntity
import kotlinx.coroutines.flow.Flow

/**
 * Local data source concrete implementation class.
 *
 * @author marlonlom
 *
 * @property newBookDao New books dao.
 * @property newBookDetailDao New book details dao.
 */
class LocalDataSourceImpl(private val newBookDao: NewBookDao, private val newBookDetailDao: NewBookDetailDao) :
  LocalDataSource {

  override fun listNewBooks(): Flow<List<NewBookEntity>> = newBookDao.listAll()

  override suspend fun addNewBooks(books: List<NewBookEntity>) = newBookDao.upsertBooks(books)

  override fun findNewBooksDetail(isbn13: String): Flow<NewBookDetailEntity?> = newBookDetailDao.findByIsbn13(isbn13)

  override suspend fun addNewBookDetails(books: List<NewBookDetailEntity>) = newBookDetailDao.upsertBooks(books)
}
