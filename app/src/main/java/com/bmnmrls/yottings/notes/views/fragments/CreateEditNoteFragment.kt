package com.bmnmrls.yottings.notes.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bmnmrls.yottings.databinding.FragmentCreateEditNoteBinding

class CreateEditNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateEditNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditNoteBinding.inflate(layoutInflater)
        return binding.root
    }

}