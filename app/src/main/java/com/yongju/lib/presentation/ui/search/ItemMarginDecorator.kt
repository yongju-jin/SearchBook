package com.yongju.lib.presentation.ui.search

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Applies margin to the top of each item, except the first one.
 *
 * Directions is bit mask for apply to margin in items
 * ex) direction = TOP or BOTTOM
 */
class MarginItemDecoration(
    private val margin: Int,
    private val direction: Int = LEFT or TOP or RIGHT or BOTTOM
) : RecyclerView.ItemDecoration() {

    companion object {
        const val LEFT = 1
        const val TOP = 1 shl 1
        const val RIGHT = 1 shl 2
        const val BOTTOM = 1 shl 3
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (direction and LEFT == LEFT) {
            outRect.left = margin
        }
        if (direction and RIGHT == RIGHT) {
            outRect.right = margin
        }
        if (direction and BOTTOM == BOTTOM) {
            outRect.bottom = margin
        }
        if (direction and TOP == TOP) {
            outRect.top = margin
        }
    }
}
