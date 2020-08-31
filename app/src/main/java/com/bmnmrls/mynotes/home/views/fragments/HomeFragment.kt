package com.bmnmrls.mynotes.home.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bmnmrls.mynotes.databinding.FragmentHomeBinding
import com.bmnmrls.mynotes.home.adapters.NotesAdapter
import com.bmnmrls.mynotes.home.models.NoteUI

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesAdapter.apply {
            addNotes(
                listOf(
                    NoteUI(0, "Test1", "30/08/20"),
                    NoteUI(1, "Test2", "30/08/20"),
                    NoteUI(2, "Test3", "30/08/20"),
                    NoteUI(3, "Test4", "30/08/20"),
                    NoteUI(4, "Test5", "30/08/20"),
                    NoteUI(5, "Test6", "30/08/20")
                )
            )
            listener = {}
        }
        binding.notesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = notesAdapter
        }
    }

}