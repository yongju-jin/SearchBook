package com.yongju.lib.presentation.di

import com.yongju.lib.data.repo.SearchBookRepositoryImpl
import com.yongju.lib.domain.repo.SearchBookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun provideSearchBookRepo(repo: SearchBookRepositoryImpl): SearchBookRepository
}