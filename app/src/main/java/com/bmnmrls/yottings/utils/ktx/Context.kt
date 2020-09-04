package com.bmnmrls.yottings.utils.ktx

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.retrieveColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)

fun Context.retrieveDrawable(@DrawableRes drawableId: Int): Drawable =
    ContextCompat.getDrawable(this, drawableId)!!