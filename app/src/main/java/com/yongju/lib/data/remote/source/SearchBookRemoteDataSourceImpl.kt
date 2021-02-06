package com.yongju.lib.data.remote.source

import android.util.Log
import com.yongju.lib.data.remote.model.Document
import com.yongju.lib.data.remote.service.SearchBookService
import com.yongju.lib.data.repo.SearchBookRemoteDataSource
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SearchBookRemoteDataSourceImpl @Inject constructor(
    private val service: SearchBookService
) : SearchBookRemoteDataSource {
    override suspend fun getSearchBook(
        keyword: String,
        searchMethod: SearchMethod,
        page: Int,
    ): Result<Pair<List<BookInfo>, Boolean>> {
        return kotlin.runCatching {
            val target = searchMethod.name.toLowerCase()

            val response = service.getSearchBook(query = keyword, target = target, page = page)
            Log.d("search", "meta: ${response.meta}")
            response.documents.map { it.toEntity() } to response.meta.isEnd
        }
    }

    private fun Document.toEntity(): BookInfo =
        BookInfo(
            id = 0,
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
            url = url,
            isFavorite = false
        )
}