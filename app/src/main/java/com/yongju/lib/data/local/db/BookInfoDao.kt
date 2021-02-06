package com.yongju.lib.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookInfoDao {
    @Query("SELECT * FROM BookInfo")
    fun getAll(): Flow<List<BookInfo>>

    @Insert
    fun insertAll(vararg bookInfo: BookInfo)

    @Query("DELETE FROM BookInfo")
    fun deleteAll()
}