package com.yongju.lib.domain.usecase

import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.repo.SearchBookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBook @Inject constructor(private val repo: SearchBookRepository) {

    suspend operator fun invoke(
        keyword: String,
        searchMethod: SearchMethod
    ): Flow<List<BookInfo>> {
        return repo.searchBook(keyword, searchMethod)
    }
}