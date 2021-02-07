package com.yongju.lib.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookInfoDao {
    @Query("SELECT * FROM BookInfo")
    fun getAll(): Flow<List<BookInfo>>

    @Insert
    fun insertAll(vararg bookInfo: BookInfo)

    @Query("DELETE FROM BookInfo")
    fun deleteAll()

    @Query("UPDATE BookInfo SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: Long, isFavorite: Boolean)
}