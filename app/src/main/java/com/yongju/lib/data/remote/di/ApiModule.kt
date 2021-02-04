package com.yongju.lib.data.remote.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yongju.lib.data.remote.service.SearchBookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provide(@ApplicationContext context: Context): SearchBookService =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(LocalDateTimeAdapter())
                    .build()
            ))
            .client(makeOkHttpClient(context))
            .baseUrl("https://dapi.kakao.com/")
            .build()
            .create(SearchBookService::class.java)

    private fun initHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun makeOkHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 50 * 1024 * 1024)) // 50 MiB
            .addInterceptor(HeaderInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(initHttpLoggingInterceptor())
            .build()

    class HeaderInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                    .newBuilder()
                    .addRequestIdentifiers()
                    .build()

            return chain.proceed(request)
        }

        private fun Request.Builder.addRequestIdentifiers(): Request.Builder {
            return addHeader("Authorization", "KakaoAK f3e04a639f5162e0dfcb1d79030f38a5")
        }
    }
}