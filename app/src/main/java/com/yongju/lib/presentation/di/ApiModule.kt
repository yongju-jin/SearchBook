package com.yongju.lib.presentation.di

import android.content.Context
import com.yongju.lib.data.remote.service.SearchBookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provide(@ApplicationContext context: Context): SearchBookService =
        Retrofit.Builder()
            .client(makeOkHttpClient(context))
            .baseUrl("https://developers.kakaok.com/")
            .build()
            .create(SearchBookService::class.java)

    private fun initHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun makeOkHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 50 * 1024 * 1024)) // 50 MiB
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(initHttpLoggingInterceptor())
            .build()
}