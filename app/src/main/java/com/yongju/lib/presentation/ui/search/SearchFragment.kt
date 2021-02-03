package com.yongju.lib.presentation.ui.search

import androidx.fragment.app.Fragment
import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentSearchBinding
import com.yongju.lib.presentation.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun layoutResId() = R.layout.fragment_search

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}