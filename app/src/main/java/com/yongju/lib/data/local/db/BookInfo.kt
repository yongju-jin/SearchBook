package com.yongju.lib.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class BookInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val authors: List<String>,
    val contents: String,
    val dateTime: LocalDate?,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
    val isFavorite: Boolean = false
)

