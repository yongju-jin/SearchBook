package com.yongju.lib.data.repo

import com.yongju.lib.domain.entity.BookInfo
import kotlinx.coroutines.flow.Flow

interface SearchBookLocalDateSource {
    suspend fun getAll(): Flow<List<BookInfo>>
    suspend fun updateSearchBook(bookInfos: List<BookInfo>) : Result<Unit>
    suspend fun clear(): Result<Unit>
}