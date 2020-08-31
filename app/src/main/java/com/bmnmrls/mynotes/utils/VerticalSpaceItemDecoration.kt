package com.bmnmrls.mynotes.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    private val verticalSpaceHeight: Int,
    private val addToLastItem: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let {
            if (addToLastItem) {
                outRect.bottom = verticalSpaceHeight
            } else {
                if (parent.getChildAdapterPosition(view) != it.itemCount - 1) {
                    outRect.bottom = verticalSpaceHeight
                }
            }
        }
    }

}