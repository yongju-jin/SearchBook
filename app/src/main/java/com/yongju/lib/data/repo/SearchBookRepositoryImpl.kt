package com.yongju.lib.data.repo

import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.repo.SearchBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Named

class SearchBookRepositoryImpl @Inject constructor(
    private val remote: SearchBookRemoteDataSource,
    private val local: SearchBookLocalDateSource,
) : SearchBookRepository {

    private var latestKeyword: String? = null
    private var latestSearchMethod: SearchMethod? = null
    private val page = AtomicInteger(1)
    private var isEnd = false

    override suspend fun searchBook(
        keyword: String,
        searchMethod: SearchMethod
    ): Flow<List<BookInfo>> {

        latestKeyword = keyword
        latestSearchMethod = searchMethod
        page.set(1)

        local.clear().getOrThrow()

        val remoteResponse = remote.getSearchBook(keyword, searchMethod, page.get()).getOrThrow()
        isEnd = remoteResponse.second
        local.updateSearchBook(remoteResponse.first).getOrThrow()
        return local.getAll()
    }

    override suspend fun searchMore(): Result<Unit> {
        val latestKeyword = this.latestKeyword
        val latestSearchMethod = this.latestSearchMethod
        return when {
            isEnd -> {
                Result.success(Unit)
            }
            (latestKeyword != null && latestSearchMethod != null) -> kotlin.runCatching {
                val remoteResponse =
                    remote.getSearchBook(latestKeyword, latestSearchMethod, page.addAndGet(1))
                        .getOrThrow()
                isEnd = remoteResponse.second
                local.updateSearchBook(remoteResponse.first).getOrThrow()
            }
            else ->
                Result.failure(IllegalStateException("latestKeyword or latestSearchMethod is null"))
        }
    }

    override suspend fun updateFavorite(id: Long, isFavorite: Boolean): Result<Unit> {
        return local.updateFavorite(id, isFavorite)
    }
}