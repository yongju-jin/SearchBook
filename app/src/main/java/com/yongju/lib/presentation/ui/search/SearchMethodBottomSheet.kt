package com.yongju.lib.presentation.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yongju.lib.R
import com.yongju.lib.databinding.DialogSearchMethodBinding
import com.yongju.lib.domain.entity.SearchMethod

class SearchMethodBottomSheet(
    context: Context,
    private val selectedSearchMethod: SearchMethod,
    private val callback: (SearchMethod) -> Unit
) : BottomSheetDialog(context, R.style.BottomSheet) {

    private lateinit var binding: DialogSearchMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogSearchMethodBinding.inflate(LayoutInflater.from(context)).also {
            it.selectedSearchMethod = selectedSearchMethod
        }
        setContentView(binding.root)

        setCanceledOnTouchOutside(true)
        setCancelable(true)

        val callbackWrapper = { searchMethod: SearchMethod ->
            callback(searchMethod)
            dismiss()
        }

        binding.layoutSearchMethodTitle.onClickSearchMethod = callbackWrapper
        binding.layoutSearchMethodIsbn.onClickSearchMethod = callbackWrapper
        binding.layoutSearchMethodPublisher.onClickSearchMethod = callbackWrapper
        binding.layoutSearchMethodPerson.onClickSearchMethod = callbackWrapper
    }
}