package com.yongju.lib.data.local.di

import android.content.Context
import androidx.room.Room
import com.yongju.lib.data.local.db.BookDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideBookDataBase(@ApplicationContext context: Context): BookDataBase =
        Room.databaseBuilder(context, BookDataBase::class.java, "BookInfo")
            .allowMainThreadQueries()
            .build()

}