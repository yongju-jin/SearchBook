package com.yongju.lib.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.yongju.lib.R
import com.yongju.lib.databinding.ActivityMainBinding
import com.yongju.lib.presentation.base.BaseActivity
import com.yongju.lib.presentation.ui.detail.DetailFragment
import com.yongju.lib.presentation.ui.search.SearchFragment
import com.yongju.lib.presentation.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val vm by viewModels<MainViewModel>()
    override fun layoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeCommand()
        setFragment(SearchFragment.newInstance(), false)
    }

    private fun observeCommand() = observeEvent(vm.command) { command ->
        when(command) {
            is MainViewModel.Command.GoToDetail -> setFragment(DetailFragment.newInstance(command.bookInfo))
        }
    }

    private fun setFragment(fragment: Fragment, addToBackStack: Boolean = true) = supportFragmentManager.commit {
        if (addToBackStack) addToBackStack(null)
        replace(binding.container.id, fragment)
    }
}