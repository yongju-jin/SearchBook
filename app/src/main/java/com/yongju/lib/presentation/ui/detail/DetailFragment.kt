package com.yongju.lib.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yongju.lib.R
import com.yongju.lib.databinding.FragmentDetailBinding
import com.yongju.lib.domain.entity.BookInfo
import com.yongju.lib.presentation.base.BaseFragment
import com.yongju.lib.presentation.ui.MainViewModel
import com.yongju.lib.presentation.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun layoutResId() = R.layout.fragment_detail

    private val vm by viewModels<DetailViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            binding.vm = vm
            binding.activityVM = activityVM
        }
    }

    companion object {
        fun newInstance(bookInfo: BookInfo): DetailFragment = DetailFragment().apply {
            arguments = bundleOf(
                "bookInfo" to bookInfo
            )
        }
    }
}