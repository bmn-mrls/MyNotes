package com.bmnmrls.yottings.utils

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    @DimenRes private val space: Int,
    private val addToLastItem: Boolean = true,
    @DimenRes private val firstItemAdditionalSpace: Int? = null,
    @DimenRes private val lastItemAdditionalSpace: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let {
            if (it.itemCount > 0) {
                setupVerticalSpace(outRect, view, parent, it)
            }
        }
    }

    private fun setupVerticalSpace(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    ) {
        val childPosition = parent.getChildAdapterPosition(view)
        if (childPosition == 0) {
            outRect.top =
                firstItemAdditionalSpace?.let { view.resources.getDimensionPixelOffset(it) } ?: 0
        }
        if (addToLastItem) {
            outRect.bottom = view.resources.getDimensionPixelOffset(space)
            if (childPosition == adapter.itemCount - 1) {
                outRect.bottom =
                    view.resources.getDimensionPixelOffset(space) + (lastItemAdditionalSpace?.let {
                        view.resources.getDimensionPixelOffset(it)
                    } ?: 0)
            }
        } else {
            if (childPosition != adapter.itemCount - 1) {
                outRect.bottom = view.resources.getDimensionPixelOffset(space)
            }
        }
    }

}