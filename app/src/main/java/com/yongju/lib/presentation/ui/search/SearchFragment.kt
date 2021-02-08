package com.yongju.lib.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentSearchBinding
import com.yongju.lib.domain.entity.SearchMethod
import com.yongju.lib.presentation.base.BaseFragment
import com.yongju.lib.presentation.ui.MainViewModel
import com.yongju.lib.presentation.ui.SharedTransitionable
import com.yongju.lib.presentation.util.dp
import com.yongju.lib.presentation.util.observe
import com.yongju.lib.presentation.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), SharedTransitionable {
    override fun layoutResId() = R.layout.fragment_search

    private val vm by viewModels<SearchViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private var transitionView: List<View> = listOf()

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
        adapter = BookListAdapter { binding, bookInfo ->
            transitionView = listOf(
                binding.ivImg,
                binding.ivFavorite
            )
            activityVM.selectBook(bookInfo)
        }

        doOnScrolled { recyclerView ->
            if (recyclerView.layoutManager.shouldMore) vm.loadMore()
        }
    }

    private fun RecyclerView.doOnScrolled(block: (RecyclerView) -> Unit) {
        addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) =
                    block(recyclerView)
            }
        )
    }

    private fun showSearchMethodMenu(searchMethod: SearchMethod) = context?.let {
        SearchMethodBottomSheet(
            it,
            searchMethod,
            vm::onSearchMethod
        ).show()
    }

    private fun subscribeState() = observe(vm.state) { state ->
        (binding.rvBooks.adapter as? BookListAdapter)?.submitList(state.books)
    }

    private fun subscribeViewCommand() = observeEvent(vm.command) { command ->
        when (command) {
            is SearchViewModel.Command.ShowErrorToast -> showErrorToast(command.msg)
            is SearchViewModel.Command.ShowSearchMethodMenu -> showSearchMethodMenu(command.searchMethod)
        }
    }

    private fun showErrorToast(msg: String) {
    }


    private inline val RecyclerView.LayoutManager?.shouldMore: Boolean
        get() {
            if (this !is LinearLayoutManager) return false
            val visibleItemCount = childCount
            val totalItemCount = itemCount
            val firstVisibleItemPosition = findFirstVisibleItemPosition()

            return (visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 1 && firstVisibleItemPosition >= 0
        }


    override fun getSharedElement(): List<Pair<View, String>> {
        return transitionView.map {
            it to it.transitionName
        }
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}