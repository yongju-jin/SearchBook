package com.yongju.lib.presentation.ui.detail

import androidx.lifecycle.*
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.usecase.UpdateFavorite
import com.yongju.lib.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saved: SavedStateHandle,
    private val updateFavorite: UpdateFavorite,
) : BaseViewModel<DetailViewModel.Command>() {

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

    val isFavorite = state.map(State::isFavorite).distinctUntilChanged()

    init {
        val bookInfo: BookInfo? = saved["bookInfo"]
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

    fun onFavorite(currentSelected: Boolean) {
        launch (
            e = {
                updateCommand(Command.ShowErrorToast)
            },
            block = {
                updateState { state ->
                    val id = state.id
                    val isFavorite = currentSelected.not()
                    updateFavorite(id, isFavorite).getOrThrow()
                    state.copy(isFavorite = currentSelected.not())
                }
            }
        )
    }
}