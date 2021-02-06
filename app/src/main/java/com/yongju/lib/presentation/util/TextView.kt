package com.yongju.lib.presentation.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.yongju.lib.R
import com.yongju.lib.domain.entity.SearchMethod
import kotlinx.android.synthetic.main.fragment_search.view.*
import java.time.LocalDate

@BindingAdapter("date")
fun TextView.setDate(date: LocalDate?) {
    if (date == null) return
    text = date.toString()
}

@BindingAdapter("searchMethod")
fun TextView.setDate(searchMethod: SearchMethod?) {
    if (searchMethod == null) return
    setText(
        when (searchMethod) {
            SearchMethod.Title -> R.string.dialog_search_method_title
            SearchMethod.ISBN -> R.string.dialog_search_method_isbn
            SearchMethod.Publisher -> R.string.dialog_search_method_publisher
            SearchMethod.Person -> R.string.dialog_search_method_person
        }
    )
}
