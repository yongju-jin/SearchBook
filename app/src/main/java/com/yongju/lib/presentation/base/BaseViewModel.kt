package com.yongju.lib.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongju.lib.presentation.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<Command> : ViewModel() {

    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command

    protected fun updateCommand(command: Command) {
        _command.value = Event(command)
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        e: (Throwable) -> Unit,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable -> e(throwable) }
        return viewModelScope.launch(
            context = context + exceptionHandler,
            block = block
        )
    }
}