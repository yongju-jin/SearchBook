package com.yongju.lib.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.usecase.SearchBook
import com.yongju.lib.domain.usecase.SearchMore
import com.yongju.lib.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBook: SearchBook,
    private val searchMore: SearchMore
) : BaseViewModel<SearchViewModel.Command>() {

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

    private val keyword = MutableStateFlow("")
    private val searchMethod = MutableStateFlow(SearchMethod.Title)

    private var loadMoreJob: Job? = null

    init {
        keyword
            .combine(searchMethod, ::Pair)
            .debounce(300)
            .conflate()
            .filter { (keyword, _) -> keyword.isNotEmpty() }
            .flatMapLatest { (keyword, method) ->
                searchBook(keyword, method)
            }
            .onEach { bookInfos ->
                updateState { state ->
                    state.copy(books = bookInfos)
                }
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

    fun onSearch(keyword: String) {
        launch(
            e = ::handleError,
            block = {
                this@SearchViewModel.keyword.emit(keyword)
            })
    }

    private fun handleError(e: Throwable) {
        updateCommand(Command.ShowErrorToast())
    }

    fun loadMore() {
        if (loadMoreJob?.isActive == true) return

        loadMoreJob = launch(
            e = ::handleError,
            block = {
                searchMore().getOrThrow()
            }
        )
    }

    fun showSearchMethodMenu() {
        updateCommand(Command.ShowSearchMethodMenu(searchMethod.value))
    }

    fun onSearchMethod(searchMethod: SearchMethod) {
        launch(
            e = ::handleError,
            block = {
                this@SearchViewModel.searchMethod.emit(searchMethod)
            }
        )
    }
}
