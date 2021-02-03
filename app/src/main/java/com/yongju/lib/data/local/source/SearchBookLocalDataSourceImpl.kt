package com.yongju.lib.data.local.source

import com.yongju.lib.data.repo.SearchBookDataSource
import javax.inject.Inject

class SearchBookLocalDataSourceImpl @Inject constructor() :
    SearchBookDataSource {
    override suspend fun getSearchBook() {
        TODO("Not yet implemented")
    }
}