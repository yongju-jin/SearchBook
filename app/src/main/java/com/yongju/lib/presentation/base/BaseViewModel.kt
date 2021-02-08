package com.yongju.lib.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yongju.lib.presentation.util.Event

abstract class BaseViewModel<Command> : ViewModel() {

    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command

    protected fun updateCommand(command: Command) {
        _command.value = Event(command)
    }
}