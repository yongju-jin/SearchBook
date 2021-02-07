package com.yongju.lib.presentation.ui

import android.view.View

fun interface SharedTransitionable {
    fun getSharedElement(): List<Pair<View, String>>
}
