package com.yongju.lib.domain.usecase

import com.yongju.lib.domain.repo.SearchBookRepository
import javax.inject.Inject

class SearchMore @Inject constructor(private val repo: SearchBookRepository) {

    suspend operator fun invoke(): Result<Unit> {
        return repo.searchMore()
    }
}