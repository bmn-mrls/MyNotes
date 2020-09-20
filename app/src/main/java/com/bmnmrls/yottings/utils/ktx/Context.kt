package com.bmnmrls.yottings.utils.ktx

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bmnmrls.yottings.utils.Color
import kotlin.random.Random

fun Context.retrieveColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)

fun Context.retrieveDrawable(@DrawableRes drawableId: Int): Drawable =
    ContextCompat.getDrawable(this, drawableId)!!

fun Context.getRandomColorStringPair(): Pair<String, String> {
    val firstColor = getRandomColorString()
    val secondColor = getRandomColorString()
    if (firstColor == secondColor) getRandomColorStringPair()
    return Pair(firstColor, secondColor)
}

fun Context.getRandomColorString(): String =
    this.getString(Color.colors[Random.nextInt(0, Color.colors.size - 1)])