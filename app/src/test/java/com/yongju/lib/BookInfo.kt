package com.yongju.lib

import com.yongju.lib.domain.entity.BookInfo
import java.time.LocalDate

fun getBookInfo(): BookInfo {
    return BookInfo(
        id = 1,
        authors = listOf("test_authors"),
        contents = "test_contents",
        dateTime = LocalDate.now(),
        isbn = "test_isbn",
        price = 4982,
        salePrice = 123409,
        publisher = "test_publisher",
        status = "test_status",
        thumbnail = "test_thumbnail",
        title = "test_title",
        translators = listOf("test_translators"),
        url = "test_url",
        isFavorite = false
    )
}