package com.yongju.lib.data.remote.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter {

    @ToJson
    fun toJson(localDate: LocalDate): String {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    @FromJson
    fun fromJson(time: String): LocalDate {
        return LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }
}