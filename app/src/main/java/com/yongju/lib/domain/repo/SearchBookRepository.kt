package com.yongju.lib.domain.repo

import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import kotlinx.coroutines.flow.Flow

interface SearchBookRepository {
    suspend fun searchBook(keyword: String, searchMethod: SearchMethod): Flow<List<BookInfo>>
    suspend fun searchMore(): Result<Unit>
    suspend fun updateFavorite(id: Long, isFavorite: Boolean): Result<Unit>
}