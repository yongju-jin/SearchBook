package com.yongju.lib.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.presentation.ui.detail.DetailViewModel
import com.yongju.lib.presentation.ui.search.SearchViewModel
import com.yongju.lib.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    sealed class Command {
        data class GoToDetail(val bookInfo: BookInfo) : Command()
    }

    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command


    fun selectBook(bookInfo: BookInfo) {
        updateCommand(
            Command.GoToDetail(bookInfo)
        )
    }

    private fun updateCommand(command: Command) {
        _command.value = Event(command)
    }
}