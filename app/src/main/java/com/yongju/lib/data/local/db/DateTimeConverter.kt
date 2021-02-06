package com.yongju.lib.data.local.db

import androidx.room.TypeConverter
import java.time.LocalDate

class Converter {
    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun dateToLocalDate(date: String?): LocalDate? {
        return date?.let {
            LocalDate.parse(date)
        }
    }

    @TypeConverter
    fun listToString(value: List<String>?): String? {
        return value?.let {
            it.joinToString()
        }
    }

    @TypeConverter
    fun jsonToList(value: String?): List<String>? {
        return value?.let {
            it.split(",").toList()
        }
    }
}
