package com.yongju.lib.data.remote.service

import com.yongju.lib.data.remote.model.SearchResponse
import com.yongju.lib.domain.entity.BookInfo
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface SearchBookService {

    // restapi key = f3e04a639f5162e0dfcb1d79030f38a5
    @GET("/v3/search/book")
    suspend fun getSearchBook(
        @Query("query") query: String,
        @Query("target") target: String,
        @Query("size") size: Int = 50,
        @Query("page") page: Int,
    ) : SearchResponse
}