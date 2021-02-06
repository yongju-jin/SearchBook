package com.yongju.lib.data.remote.source

import com.yongju.lib.data.remote.model.Document
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
            val target = searchMethod.name.toLowerCase()

            service.getSearchBook(query = keyword, target = target).documents.map {
                it.toEntity()
            }
        }
    }

    private fun Document.toEntity(): BookInfo =
        BookInfo(
            authors = authors,
            contents = contents,
            dateTime = try {
                LocalDate.parse(datetime, DateTimeFormatter.ISO_DATE_TIME)
            } catch (e: Exception) {
                null
            },
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