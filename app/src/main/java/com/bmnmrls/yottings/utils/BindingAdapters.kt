package com.bmnmrls.yottings.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(
    value = [
        "firstColor",
        "secondColor"
    ],
    requireAll = true
)
fun ImageView.setGradientBackground(firstColor: String, secondColor: String) =
    setImageDrawable(getGradientBackground(firstColor, secondColor))

private fun getGradientBackground(firstColor: String, secondColor: String): GradientDrawable =
    GradientDrawable(
        GradientDrawable.Orientation.BOTTOM_TOP,
        intArrayOf(
            Color.parseColor(firstColor),
            Color.parseColor(secondColor)
        )
    ).apply {
        cornerRadius = 0f
    }