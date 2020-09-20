package com.bmnmrls.yottings.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

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

@SuppressLint("SimpleDateFormat")
@BindingAdapter(value = ["dateToString"])
fun TextView.dateToString(date: Date) {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    text = simpleDateFormat.format(date)
}