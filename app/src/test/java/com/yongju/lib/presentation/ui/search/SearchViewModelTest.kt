package com.yongju.lib.presentation.ui.search

import com.yongju.lib.base.BaseTest
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.domain.usecase.SearchBook
import com.yongju.lib.domain.usecase.SearchMore
import com.yongju.lib.getBookInfo
import com.yongju.lib.presentation.ui.search.SearchViewModel.Command.ShowSearchMethodMenu
import com.yongju.lib.presentation.util.Event
import com.yongju.lib.util.getOrAwaitValue
import com.yongju.lib.util.testWithTriggers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest : BaseTest() {

    private val searchBook: SearchBook = mock()
    private val searchMore: SearchMore = mock()

    private lateinit var vm: SearchViewModel

    @Before
    fun setUp() {
        vm = SearchViewModel(searchBook, searchMore)
    }

    @Test
    fun onSearch() = runBlockingTest {
        val keyword = "test_keyword"
        val bookInfos = listOf(getBookInfo())
        val expected = listOf(
            SearchViewModel.State(books = emptyList()),
            SearchViewModel.State(books = bookInfos)
        )

        When calling searchBook.invoke(
            keyword,
            SearchMethod.Title
        ) itReturns flow { emit(bookInfos) }


        val actual = vm.state.testWithTriggers {
            vm.onSearch(keyword)
        }.data

        delay(1000)

        actual shouldBeEqualTo expected
    }

    @Test
    fun showSearchMethodMenu() = runBlockingTest {
        val searchMethod = SearchMethod.ISBN
        val expected = Event(ShowSearchMethodMenu(searchMethod))

        vm.onSearchMethod(searchMethod)
        vm.showSearchMethodMenu()

        val actual = vm.command.getOrAwaitValue()

        actual shouldBeEqualTo expected
    }

    @Test
    fun onSearchMethod() = runBlockingTest {
        val keyword = "test_keyword"
        val bookInfos = listOf(getBookInfo())
        val expected = listOf(
            SearchViewModel.State(books = emptyList()),
            SearchViewModel.State(books = bookInfos)
        )

        When calling searchBook.invoke(
            keyword,
            SearchMethod.Title
        ) itReturns flow { emptyList<BookInfo>() }

        When calling searchBook.invoke(
            keyword,
            SearchMethod.Person
        ) itReturns flow { emit(bookInfos) }

        val actual = vm.state.testWithTriggers {
            vm.onSearch(keyword)
            vm.onSearchMethod(SearchMethod.Person)
        }.data

        delay(1000)

        actual shouldBeEqualTo expected
    }
}