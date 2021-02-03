package com.yongju.lib.data.remote.source

import com.yongju.lib.data.repo.SearchBookDataSource
import javax.inject.Inject

class SearchBookRemoteDataSourceImpl @Inject constructor() :
    SearchBookDataSource {
    override suspend fun getSearchBook() {
        TODO("Not yet implemented")
    }
}