package com.yongju.lib.data.repo

import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod

interface SearchBookRemoteDataSource {
    suspend fun getSearchBook(keyword: String, searchMethod: SearchMethod, page: Int): Result<List<BookInfo>>
}