package com.yongju.lib.data.remote.source

import android.util.Log
import com.yongju.lib.data.remote.model.Document
import com.yongju.lib.data.remote.model.SearchResponse
import com.yongju.lib.data.remote.service.SearchBookService
import com.yongju.lib.data.repo.SearchBookDataSource
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SearchBookRemoteDataSourceImpl @Inject constructor(
    private val service: SearchBookService
) : SearchBookDataSource {
    override suspend fun getSearchBook(
        keyword: String,
        searchMethod: SearchMethod
    ): Result<List<BookInfo>> {
        return kotlin.runCatching {
            service.getSearchBook(query = keyword).documents.map {
                it.toEntity()
            }
        }
    }

    private fun Document.toEntity(): BookInfo =
        BookInfo(
            authors = authors,
            contents = contents,
            dateTime = LocalDate.parse(datetime, DateTimeFormatter.ISO_DATE_TIME),
            isbn = isbn,
            price = price,
            publisher = publisher,
            salePrice = salePrice,
            status = status,
            thumbnail = thumbnail,
            title = title,
            translators = translators,
            url = url
        )

}