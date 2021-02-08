package com.yongju.lib.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yongju.lib.util.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    internal val testRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    internal val coroutinesRule = CoroutinesTestRule()
}