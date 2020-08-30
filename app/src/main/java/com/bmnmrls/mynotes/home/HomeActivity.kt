package com.bmnmrls.mynotes.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bmnmrls.mynotes.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, HomeActivity::class.java))
        }
    }

}