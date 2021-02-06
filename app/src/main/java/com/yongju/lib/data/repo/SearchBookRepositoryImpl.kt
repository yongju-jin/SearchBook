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
    private var isAvailableLoadMore = false

    override suspend fun searchBook(
        keyword: String,
        searchMethod: SearchMethod
    ): Flow<List<BookInfo>> {

        latestKeyword = keyword
        latestSearchMethod = searchMethod
        page.set(1)

        local.clear().getOrThrow()
        local.updateSearchBook(
            remote.getSearchBook(keyword, searchMethod, page.get()).getOrThrow().also {
                isAvailableLoadMore = it.size == 50
            }
        ).getOrThrow()
        return local.getAll()
    }

    override suspend fun searchMore(): Result<Unit> {
        val latestKeyword = this.latestKeyword
        val latestSearchMethod = this.latestSearchMethod
        return when {
            !isAvailableLoadMore -> {
                Result.success(Unit)
            }
            (latestKeyword != null && latestSearchMethod != null) -> {
                val response =
                    remote.getSearchBook(latestKeyword, latestSearchMethod, page.addAndGet(1))
                        .getOrThrow()
                local.updateSearchBook(response)
            }
            else ->
                Result.failure(IllegalStateException("latestKeyword or latestSearchMethod is null"))
        }
    }
}