package com.yongju.lib.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yongju.lib.util.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {

    @get:Rule
    internal val testRule = InstantTaskExecutorRule()

    @get:Rule
    internal val coroutinesRule = CoroutinesTestRule()

    protected fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        coroutinesRule.testDispatcher.runBlockingTest(block)
    }
}