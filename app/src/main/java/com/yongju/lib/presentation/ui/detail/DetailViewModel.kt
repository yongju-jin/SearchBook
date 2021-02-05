package com.yongju.lib.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.presentation.util.Event
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DetailViewModel @Inject constructor(
    private val saved: SavedStateHandle
) : ViewModel() {

    sealed class Command {
        object ShowErrorToast: Command()
    }

    data class State(
        val book: BookInfo? = null
    )

    private val _state = MutableLiveData(State())
    val state : LiveData<State>
        get() = _state

    private val _command = MutableLiveData<Event<Command>>()
    val command : LiveData<Event<Command>>
        get() = _command

    init {
        val bookInfo: BookInfo? = saved["bookInfo"]
        Log.d("detail", "bookInfo: $bookInfo")
        if (bookInfo == null) {
            updateCommand(Command.ShowErrorToast)
        } else {
            updateState { state ->
                state.copy(book = bookInfo)
            }
        }
    }

    private inline fun updateState(block: (State) -> State) {
        _state.value = block(requireNotNull(_state.value))
    }

    private fun updateCommand(command: Command) {
        _command.value = Event(command)
    }
}