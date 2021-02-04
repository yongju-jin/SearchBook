package com.yongju.lib.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentSearchBinding
import com.yongju.lib.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun layoutResId() = R.layout.fragment_search
    private val vm by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Search", "before called search")
        vm.search("kotlin")
        Log.d("Search", "after called search")
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}