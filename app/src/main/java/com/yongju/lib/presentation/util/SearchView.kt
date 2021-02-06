package com.yongju.lib.presentation.util

import android.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("onSearchChanged")
fun SearchView.onSearchChanged(callback: OnSearchChanged?) {
    if (callback == null) return

    setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let(callback::onSearchChanged)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let(callback::onSearchChanged)
                return false
            }
        }
    )
}

interface OnSearchChanged {
    fun onSearchChanged(query: String)
}