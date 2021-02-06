package com.yongju.lib.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BookInfo::class], version = 1)
@TypeConverters(Converter::class)
abstract class BookDataBase : RoomDatabase() {
    abstract fun bookInfoDao(): BookInfoDao
}