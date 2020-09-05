package com.bmnmrls.yottings.notes.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.databinding.FragmentCreateEditNoteBinding
import com.bmnmrls.yottings.utils.ktx.hideKeyboard

class CreateEditNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateEditNoteBinding

    enum class Mode {
        CREATE,
        EDIT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupOnBackPressed()
    }

    private fun setupUI() {
        setupDoneButton()
    }

    private fun setupOnBackPressed() {
        val doneButton = activity?.findViewById<Button>(R.id.doneButton)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                doneButton?.visibility = View.GONE
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setupDoneButton() {
        val doneButton = activity?.findViewById<Button>(R.id.doneButton)
        doneButton?.apply {
            visibility = View.VISIBLE
            setOnClickListener { activity?.hideKeyboard() }
        }
    }

}