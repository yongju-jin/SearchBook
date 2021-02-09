package com.yongju.lib.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.room.Update
import com.yongju.lib.base.BaseTest
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.usecase.UpdateFavorite
import com.yongju.lib.getBookInfo
import com.yongju.lib.presentation.ui.search.SearchViewModel
import com.yongju.lib.util.testWithTriggers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.amshove.kluent.*

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@ExperimentalCoroutinesApi
class DetailViewModelTest : BaseTest() {

    private lateinit var vm: DetailViewModel

    private val savedStateHandle: SavedStateHandle = mock()
    private val updateFavorite: UpdateFavorite = mock()

    private val bookInfo = getBookInfo()

    @Before
    fun setUp() {
        When calling savedStateHandle.get<BookInfo>("bookInfo") itReturns bookInfo
        vm = DetailViewModel(
            savedStateHandle,
            updateFavorite
        )
    }

    @Test
    fun onFavorite() = runBlockingTest {
        When calling updateFavorite.invoke(bookInfo.id, true) itReturns Result.success(Unit)

        val expected = listOf(
            DetailViewModel.State(
                id = bookInfo.id,
                img = bookInfo.thumbnail,
                title = bookInfo.title,
                publishDate = bookInfo.dateTime,
                price = bookInfo.price,
                publisher = bookInfo.publisher,
                contents = bookInfo.contents,
                isFavorite = bookInfo.isFavorite
            ),
            DetailViewModel.State(
                id = bookInfo.id,
                img = bookInfo.thumbnail,
                title = bookInfo.title,
                publishDate = bookInfo.dateTime,
                price = bookInfo.price,
                publisher = bookInfo.publisher,
                contents = bookInfo.contents,
                isFavorite = false
            ),
        )

        val actual = vm.state.testWithTriggers {
            vm.onFavorite(true)
        }.data

        delay(1000)

        actual shouldBeEqualTo expected
    }
}