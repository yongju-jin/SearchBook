package com.yongju.lib.data.remote.service

import retrofit2.http.GET

interface SearchBookService {

    @GET("/v3/search/book")
    suspend fun getSearchBook()
}