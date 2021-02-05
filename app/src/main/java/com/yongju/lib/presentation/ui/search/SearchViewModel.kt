package com.yongju.lib.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.usecase.SearchBook
import com.yongju.lib.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBook: SearchBook
) : ViewModel() {

    sealed class Command {
        data class GoToDetail(val bookInfo: BookInfo): Command()
    }

    data class State(
        val books: List<BookInfo> = listOf()
    )

    private val _state = MutableLiveData(State())
    val state : LiveData<State>
        get() = _state

    private val _command = MutableLiveData<Event<Command>>()
    val command : LiveData<Event<Command>>
        get() = _command

    private val keyword = MutableStateFlow("")
    private val searchMethod = MutableStateFlow(SearchMethod.Title)

    init {
        keyword.combine(searchMethod, ::Pair)
            .debounce(300)
            .conflate()
            .filter { (keyword, _) -> keyword.isNotEmpty() }
            .onEach { (keyword, method) ->
                Log.d("Search", "keyword: $keyword, method : $method")
                searchBook(keyword, method).fold(
                    { bookInfos ->
                        bookInfos.forEach { bookInfo ->
                            Log.d("search", "bookInfo: $bookInfo")
                        }
                        updateState { state ->
                            state.copy(books = bookInfos)
                        }
                    },
                    { e -> e.printStackTrace() }
                )
            }
            .catch { e ->
                e.printStackTrace()
            }
            .launchIn(viewModelScope)
    }

    private inline fun updateState(block: (State) -> State) {
        _state.value = block(requireNotNull(_state.value))
    }

    fun search(keyword: String) = viewModelScope.launch {
        this@SearchViewModel.keyword.emit(keyword)
    }
}