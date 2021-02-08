package com.yongju.lib.presentation.ui

import com.yongju.lib.base.BaseTest
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.presentation.ui.MainViewModel.Command.GoBack
import com.yongju.lib.presentation.ui.MainViewModel.Command.GoToDetail
import com.yongju.lib.presentation.util.Event
import com.yongju.lib.util.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class MainViewModelTest : BaseTest() {

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel()
    }

    @Test
    fun testSelectBook() = runBlockingTest {
        val bookInfo = BookInfo(
            id = 1,
            authors = listOf("test_authors"),
            contents = "test_contents",
            dateTime = LocalDate.now(),
            isbn = "test_isbn",
            price = 4982,
            salePrice = 123409,
            publisher = "test_publisher",
            status = "test_status",
            thumbnail = "test_thumbnail",
            title = "test_title",
            translators = listOf("test_translators"),
            url = "test_url",
            isFavorite = false
        )
        val expected = Event(GoToDetail(bookInfo))
        vm.selectBook(bookInfo = bookInfo)

        val actual = vm.command.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testBack() {
        val expected = Event(GoBack)
        vm.back()

        val actual = vm.command.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }
}