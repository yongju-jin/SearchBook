package com.yongju.lib.data.repo

import android.app.RemoteAction
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.repo.SearchBookRepository
import javax.inject.Inject
import javax.inject.Named

class SearchBookRepositoryImpl @Inject constructor(
    @Named("remote") private val remote: SearchBookDataSource,
    @Named("local") private val local: SearchBookDataSource,
) : SearchBookRepository {
    override suspend fun searchBook(
        keyword: String,
        searchMethod: SearchMethod
    ): Result<List<BookInfo>> {
        return remote.getSearchBook(keyword, searchMethod)
    }
}