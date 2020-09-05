package com.bmnmrls.yottings.utils.ktx

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.makeFullScreen() {
    this.window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
            )
}

fun Activity.hideKeyboard() {
    try {
        val imm: InputMethodManager? =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    } catch (e: Exception) {
        //No-op
    }
}