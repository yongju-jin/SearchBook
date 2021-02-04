package com.yongju.lib.presentation.ui.detail

import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentDetailBinding
import com.yongju.lib.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun layoutResId() = R.layout.fragment_detail

    companion object {
        fun newInstance(id: Int): DetailFragment = DetailFragment()
    }
}