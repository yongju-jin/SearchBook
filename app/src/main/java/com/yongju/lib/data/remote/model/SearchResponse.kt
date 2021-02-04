package com.yongju.lib.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "meta")
    val meta: Meta,

    @Json(name = "documents")
    val documents: List<Document>
)

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "is_end")
    val isEnd: Boolean,

    @Json(name = "pageable_count")
    val pageableCount: Int,

    @Json(name = "total_count")
    val totalCount: Int,
)

data class Document(
    @Json(name = "authors")
    val authors: List<String>,

    @Json(name = "contents")
    val contents: String,

    @Json(name = "datetime")
    val datetime: String,

    @Json(name = "isbn")
    val isbn: String,

    @Json(name = "price")
    val price: Int,

    @Json(name = "publisher")
    val publisher: String,

    @Json(name = "sale_price")
    val salePrice: Int,

    @Json(name = "status")
    val status: String,

    @Json(name = "thumbnail")
    val thumbnail: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "translators")
    val translators: List<String>,

    @Json(name = "url")
    val url: String,
)

