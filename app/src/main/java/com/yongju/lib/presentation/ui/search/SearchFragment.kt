package com.yongju.lib.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentSearchBinding
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.presentation.base.BaseFragment
import com.yongju.lib.presentation.ui.MainViewModel
import com.yongju.lib.presentation.util.dp
import com.yongju.lib.presentation.util.observe
import com.yongju.lib.presentation.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun layoutResId() = R.layout.fragment_search

    private val vm by viewModels<SearchViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            binding.vm = vm
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        subscribeState()
        subscribeViewCommand()
    }

    private fun initRV() = with(binding.rvBooks) {
        addItemDecoration(MarginItemDecoration(8.dp(context).toInt()))
        adapter = BookListAdapter(activityVM::selectBook)
    }

    private fun subscribeState() = observe(vm.state) { state ->
        (binding.rvBooks.adapter as? BookListAdapter)?.submitList(state.books)
    }

    private fun subscribeViewCommand() = observeEvent(vm.command) { command ->
        when (command) {
            is SearchViewModel.Command.ShowErrorToast -> TODO()
            is SearchViewModel.Command.ShowSearchMethodMenu -> showSearchMethodMenu(command.searchMethod)
        }
    }

    private fun showSearchMethodMenu(searchMethod: SearchMethod) = context?.let {
        Log.d("search", "showSearchMethodMenu")
        SearchMethodBottomSheet(
            it,
            searchMethod,
            vm::onSearchMethod
        ).show()
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}