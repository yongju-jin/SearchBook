package com.yongju.lib.presentation.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate

@BindingAdapter("date")
fun TextView.setDate(date: LocalDate?) {
    if (date == null) return
    text = date.toString()
}