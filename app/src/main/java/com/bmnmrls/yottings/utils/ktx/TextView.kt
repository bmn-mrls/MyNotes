package com.bmnmrls.yottings.utils.ktx

import android.widget.TextView

fun TextView.setTextFromResource(resource: Any) {
    when (resource) {
        is String -> text = resource
        is Int -> context.getString(resource)
        else -> throw IllegalArgumentException("The resource provided must be of type String or Int.")
    }
}