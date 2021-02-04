package com.yongju.lib.data.repo

import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod

interface SearchBookDataSource {
    suspend fun getSearchBook(keyword: String, searchMethod: SearchMethod): Result<List<BookInfo>>
}