package com.bmnmrls.yottings.notes.views.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.databinding.ActivityNotesBinding
import com.bmnmrls.yottings.utils.ktx.hideKeyboard
import com.bmnmrls.yottings.utils.ktx.setTextFromStringOrResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            findNavController(R.id.navHostFragment)
        )
        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { _, destination, _ ->
            evaluateDoneButton(destination)
            binding.toolbarTitleTextView.setTextFromStringOrResource(destination.label.toString())
        }
    }

    private fun evaluateDoneButton(destination: NavDestination) {
        binding.doneButton.setOnClickListener { this@NotesActivity.hideKeyboard() }
        binding.doneButton.visibility =
            if (destination.id == R.id.createEditNoteFragment) View.VISIBLE else View.GONE
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, NotesActivity::class.java))
        }
    }

}