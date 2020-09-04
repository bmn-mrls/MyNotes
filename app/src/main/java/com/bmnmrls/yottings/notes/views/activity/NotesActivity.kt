package com.bmnmrls.yottings.notes.views.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.databinding.ActivityNotesBinding
import com.bmnmrls.yottings.utils.ktx.setTextFromResource

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            binding.toolbar.findViewById<TextView>(R.id.toolbarTitleTextView)
                .setTextFromResource(item.title)
            findNavController(R.id.navHostFragment).navigate(item.itemId)
            true
        }
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, NotesActivity::class.java))
        }
    }

}