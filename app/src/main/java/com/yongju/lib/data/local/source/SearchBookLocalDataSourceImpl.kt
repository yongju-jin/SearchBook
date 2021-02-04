package com.yongju.lib.data.local.source

import com.yongju.lib.data.repo.SearchBookDataSource
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import javax.inject.Inject

class SearchBookLocalDataSourceImpl @Inject constructor() :
    SearchBookDataSource {
    override suspend fun getSearchBook(
        keyword: String,
        searchMethod: SearchMethod
    ): Result<List<BookInfo>> {
        return kotlin.runCatching {
            TODO("Not yet implemented")
        }
    }
}