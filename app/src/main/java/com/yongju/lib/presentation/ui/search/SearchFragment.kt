package com.yongju.lib.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentSearchBinding
import com.yongju.lib.presentation.base.BaseFragment
import com.yongju.lib.presentation.util.dp
import com.yongju.lib.presentation.util.observe
import com.yongju.lib.presentation.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun layoutResId() = R.layout.fragment_search
    private val vm by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        subscribeState()
        subscribeCommand()
        vm.search("kotlin")
    }

    private fun initRV() = with(binding.rvBooks) {
        addItemDecoration(MarginItemDecoration(8.dp(context).toInt()))
        adapter = BookListAdapter()
    }

    private fun subscribeState() = observe(vm.state) { state ->
        (binding.rvBooks.adapter as? BookListAdapter)?.submitList(state.books)
    }

    private fun subscribeCommand() = observeEvent(vm.command) { command ->
        when (command) {
            is SearchViewModel.Command.GoToDetail -> TODO()
        }
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}