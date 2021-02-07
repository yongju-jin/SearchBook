package com.yongju.lib.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.usecase.UpdateFavorite
import com.yongju.lib.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saved: SavedStateHandle,
    private val updateFavorite: UpdateFavorite,
) : ViewModel() {

    sealed class Command {
        object ShowErrorToast : Command()
    }

    data class State(
        val id: Long = -1,
        val img: String? = null,
        val title: String? = null,
        val publishDate: LocalDate? = null,
        val price: Int? = null,
        val publisher: String? = null,
        val contents: String? = null,
        val isFavorite: Boolean = false
    )

    private val _state = MutableLiveData(State())
    val state: LiveData<State>
        get() = _state

    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command

    val isFavorite = state.map(State::isFavorite).distinctUntilChanged()

    init {
        val bookInfo: BookInfo? = saved["bookInfo"]
        Log.d("detail", "bookInfo: $bookInfo")
        if (bookInfo == null) {
            updateCommand(Command.ShowErrorToast)
        } else {
            updateState { state ->
                state.copy(
                    id = bookInfo.id,
                    img = bookInfo.thumbnail,
                    title = bookInfo.title,
                    publishDate = bookInfo.dateTime,
                    price = bookInfo.price,
                    publisher = bookInfo.publisher,
                    contents = bookInfo.contents,
                    isFavorite = bookInfo.isFavorite
                )
            }
        }
    }

    private inline fun updateState(block: (State) -> State) {
        _state.value = block(requireNotNull(_state.value))
    }

    private fun updateCommand(command: Command) {
        _command.value = Event(command)
    }

    fun onFavorite(currentSelected: Boolean) {
        viewModelScope.launch {
            updateState { state ->
                val id = state.id
                val isFavorite = currentSelected.not()
                Log.d("detail", "id: ${state.id}, currentSelected: $currentSelected")
                updateFavorite(id, isFavorite).getOrThrow()
                state.copy(isFavorite = currentSelected.not())
            }
        }
    }
}