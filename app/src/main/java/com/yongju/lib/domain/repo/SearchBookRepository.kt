package com.yongju.lib.domain.repo

import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod

interface SearchBookRepository {
    suspend fun searchBook(keyword: String, searchMethod: SearchMethod): Result<List<BookInfo>>
}