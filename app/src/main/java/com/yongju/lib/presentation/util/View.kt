package com.yongju.lib.presentation.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleIf")
fun View.visibleIf(visible: Boolean?) {
    if (visible == null) return
    isVisible = visible
}

