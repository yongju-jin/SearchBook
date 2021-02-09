package com.yongju.lib.presentation.ui

import com.yongju.lib.base.BaseTest
import com.yongju.lib.getBookInfo
import com.yongju.lib.presentation.ui.MainViewModel.Command.GoBack
import com.yongju.lib.presentation.ui.MainViewModel.Command.GoToDetail
import com.yongju.lib.presentation.util.Event
import com.yongju.lib.util.getOrAwaitValue
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class MainViewModelTest : BaseTest() {

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel()
    }

    @Test
    fun testSelectBook() = runBlockingTest {
        val bookInfo = getBookInfo()

        val expected = Event(GoToDetail(bookInfo))
        vm.selectBook(bookInfo = bookInfo)

        val actual = vm.command.getOrAwaitValue()

        actual shouldBeEqualTo expected
    }

    @Test
    fun testBack() = runBlockingTest {
        val expected = Event(GoBack)
        vm.back()

        val actual = vm.command.getOrAwaitValue()

        actual shouldBeEqualTo expected
    }
}