package com.yongju.lib.data.repo

import com.yongju.lib.domain.repo.SearchBookRepository
import com.yongju.lib.domain.usecase.SearchBook
import javax.inject.Inject
import javax.inject.Named

class SearchBookRepositoryImpl @Inject constructor(
    @Named("remote") remote: SearchBookDataSource,
    @Named("local") local: SearchBookDataSource,
) : SearchBookRepository {
    // cache here
    override suspend fun searchBook() {
        TODO("Not yet implemented")
    }
}