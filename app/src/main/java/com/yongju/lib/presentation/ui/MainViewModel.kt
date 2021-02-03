package com.yongju.lib.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yongju.lib.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    sealed class Command {
        data class GoToDetail(
            val id: Int
        ) : Command()
    }

    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command
}