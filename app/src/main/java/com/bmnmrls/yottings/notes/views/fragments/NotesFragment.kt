package com.bmnmrls.yottings.notes.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.databinding.FragmentNotesBinding
import com.bmnmrls.yottings.notes.adapters.NotesAdapter
import com.bmnmrls.yottings.notes.models.NoteUI
import com.bmnmrls.yottings.utils.VerticalSpaceItemDecoration

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesAdapter.apply {
            addNotes(
                listOf(
                    NoteUI(0, "Test1", "30/08/20", false),
                    NoteUI(1, "Test2", "30/08/20", false),
                    NoteUI(2, "Test3", "30/08/20", true),
                    NoteUI(3, "Test4", "30/08/20", true),
                    NoteUI(3, "Test4", "30/08/20", false),
                    NoteUI(3, "Test4", "30/08/20", false),
                    NoteUI(3, "Test4", "30/08/20", true),
                    NoteUI(3, "Test4", "30/08/20", false),
                    NoteUI(4, "Test5", "30/08/20", false),
                    NoteUI(5, "Test6", "30/08/20", false)
                )
            )
            listener = {}
        }
        binding.notesRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                VerticalSpaceItemDecoration(
                    space = R.dimen.notes_frag_notes_vertical_space,
                    firstItemAdditionalSpace = R.dimen.notes_frag_notes_margin_top,
                    lastItemAdditionalSpace = R.dimen.notes_frag_notes_margin_bottom
                )
            )
            adapter = notesAdapter
        }
    }

}