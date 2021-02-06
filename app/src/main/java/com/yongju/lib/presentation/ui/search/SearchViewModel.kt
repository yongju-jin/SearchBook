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
        data class ShowErrorToast(val msg: String = "error") : Command()
        data class ShowSearchMethodMenu(val searchMethod: SearchMethod) : Command()
    }

    data class State(
        val books: List<BookInfo> = listOf()
    )

    private val _state = MutableLiveData(State())
    val state: LiveData<State>
        get() = _state

    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command

    private val keyword = MutableStateFlow("")
    private val searchMethod = MutableStateFlow(SearchMethod.Title)

    init {
        keyword
            .combine(searchMethod, ::Pair)
            .debounce(300)
            .conflate()
            .filter { (keyword, _) -> keyword.isNotEmpty() }
            .onEach { (keyword, method) ->
                Log.d("Search", "keyword: $keyword, method : $method")
                searchBook(keyword, method).fold(
                    { bookInfos ->
                        updateState { state ->
                            state.copy(books = bookInfos)
                        }
                    },
                    { e ->
                        e.printStackTrace()
                        updateCommand(Command.ShowErrorToast())
                    }
                )
            }
            .catch { e ->
                e.printStackTrace()
                updateCommand(Command.ShowErrorToast())
            }
            .launchIn(viewModelScope)
    }

    private inline fun updateState(block: (State) -> State) {
        _state.value = block(requireNotNull(_state.value))
    }

    private fun updateCommand(command: Command) {
        _command.value = Event(command)
    }

    fun onSearch(keyword: String) {
        Log.d("search", "keyword: $keyword")
        viewModelScope.launch {
            this@SearchViewModel.keyword.emit(keyword)
        }
        Unit
    }

    fun loadMore() {
    }

    fun showSearchMethodMenu() {
        updateCommand(Command.ShowSearchMethodMenu(searchMethod.value))
    }

    fun onSearchMethod(searchMethod: SearchMethod) {
        Log.d("search", "searchMethod: $searchMethod")
        viewModelScope.launch {
            this@SearchViewModel.searchMethod.emit(searchMethod)
        }
    }
}