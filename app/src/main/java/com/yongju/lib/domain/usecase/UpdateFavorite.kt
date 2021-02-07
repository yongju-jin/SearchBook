package com.yongju.lib.domain.usecase

import com.yongju.lib.domain.repo.SearchBookRepository
import javax.inject.Inject

class UpdateFavorite @Inject constructor(private val repo: SearchBookRepository) {

    suspend operator fun invoke(
        id: Long,
        isFavorite: Boolean
    ): Result<Unit> {
        return repo.updateFavorite(id, isFavorite)
    }
}