package com.yongju.lib.domain.entity

import java.time.LocalDate

data class BookInfo(
    val authors: List<String>,
    val contents: String,
    val dateTime: LocalDate,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val sale_price: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
)