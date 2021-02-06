package com.yongju.lib.data.local.source

import com.yongju.lib.data.local.db.BookDataBase
import com.yongju.lib.data.repo.SearchBookLocalDateSource
import com.yongju.lib.data.repo.SearchBookRemoteDataSource
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchBookLocalDataSourceImpl @Inject constructor(
    private val db: BookDataBase
) : SearchBookLocalDateSource {

    override suspend fun getAll(): Flow<List<BookInfo>> {
        return db.bookInfoDao().getAll().map { bookInfos ->
            bookInfos.map { bookInfo ->
                BookInfo(
                    id = bookInfo.id,
                    authors = bookInfo.authors,
                    contents = bookInfo.contents,
                    dateTime = bookInfo.dateTime,
                    isbn = bookInfo.isbn,
                    price = bookInfo.price,
                    publisher = bookInfo.publisher,
                    salePrice = bookInfo.salePrice,
                    status = bookInfo.status,
                    thumbnail = bookInfo.thumbnail,
                    title = bookInfo.title,
                    translators = bookInfo.translators,
                    url = bookInfo.url,
                    isFavorite = bookInfo.isFavorite
                )
            }
        }
    }

    override suspend fun clear(): Result<Unit> {
        return kotlin.runCatching {
            db.bookInfoDao().deleteAll()
        }
    }

    override suspend fun updateSearchBook(bookInfos: List<BookInfo>): Result<Unit> {
        return kotlin.runCatching {
            val dbBookInfos = bookInfos.map { bookInfo ->
                com.yongju.lib.data.local.db.BookInfo(
                    authors = bookInfo.authors,
                    contents = bookInfo.contents,
                    dateTime = bookInfo.dateTime,
                    isbn = bookInfo.isbn,
                    price = bookInfo.price,
                    publisher = bookInfo.publisher,
                    salePrice = bookInfo.salePrice,
                    status = bookInfo.status,
                    thumbnail = bookInfo.thumbnail,
                    title = bookInfo.title,
                    translators = bookInfo.translators,
                    url = bookInfo.url
                )
            }
            db.bookInfoDao().insertAll(bookInfo = dbBookInfos.toTypedArray())
        }
    }
}