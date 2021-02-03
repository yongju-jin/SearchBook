package com.yongju.lib.data.repo

interface SearchBookDataSource {
    suspend fun getSearchBook()
}