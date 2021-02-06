package com.yongju.lib.presentation.di

import com.yongju.lib.data.local.source.SearchBookLocalDataSourceImpl
import com.yongju.lib.data.remote.source.SearchBookRemoteDataSourceImpl
import com.yongju.lib.data.repo.SearchBookLocalDateSource
import com.yongju.lib.data.repo.SearchBookRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun provideSearchBookRemoteDataSource(remote: SearchBookRemoteDataSourceImpl): SearchBookRemoteDataSource

    @Binds
    @Singleton
    fun provideSearchBookLocalDataSource(remote: SearchBookLocalDataSourceImpl): SearchBookLocalDateSource
}