package com.yongju.lib.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.presentation.base.BaseViewModel
import com.yongju.lib.presentation.ui.detail.DetailViewModel
import com.yongju.lib.presentation.ui.search.SearchViewModel
import com.yongju.lib.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainViewModel.Command>() {
    sealed class Command {
        object GoBack : Command()
        data class GoToDetail(val bookInfo: BookInfo) : Command()
    }

    fun selectBook(bookInfo: BookInfo) {
        updateCommand(
            Command.GoToDetail(bookInfo)
        )
    }

    fun back() {
        updateCommand(Command.GoBack)
    }
}