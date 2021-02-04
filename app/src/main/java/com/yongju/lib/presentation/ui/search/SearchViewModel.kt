package com.yongju.lib.presentation.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.usecase.SearchBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBook: SearchBook
) : ViewModel() {

    data class State(
        val books: List<BookInfo> = listOf()
    )

    private val keyword = MutableStateFlow("")
    private val searchMethod = MutableStateFlow(SearchMethod.Title)

    init {
        keyword.combine(searchMethod, ::Pair)
            .debounce(300)
            .conflate()
            .onEach { (keyword, method) ->
                Log.d("Search", "keyword: $keyword, method : $method")
                val result = searchBook(keyword, method).getOrThrow()
                Log.d("Search", "result: $result")
            }
            .catch { e ->
                e.printStackTrace()
            }
            .launchIn(viewModelScope)
    }

    fun search(keyword: String) = viewModelScope.launch {
        this@SearchViewModel.keyword.emit(keyword)
    }
}