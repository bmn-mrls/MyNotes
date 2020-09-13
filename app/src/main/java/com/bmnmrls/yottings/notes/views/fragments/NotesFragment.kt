package com.bmnmrls.yottings.notes.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.createeditnote.views.fragments.CreateEditNoteFragment
import com.bmnmrls.yottings.databinding.FragmentNotesBinding
import com.bmnmrls.yottings.notes.adapters.NotesAdapter
import com.bmnmrls.yottings.utils.VerticalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment() {

    @Inject
    lateinit var notesAdapter: NotesAdapter
    private lateinit var binding: FragmentNotesBinding

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

        setupUI()
    }

    private fun setupUI() {
        setupNotes()
        setupAddNewNote()
    }

    private fun setupNotes() {
        notesAdapter.listener = {}
        binding.notesRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                VerticalSpaceItemDecoration(
                    space = R.dimen.notes_vertical_space,
                    firstItemAdditionalSpace = R.dimen.notes_margin_top,
                    lastItemAdditionalSpace = R.dimen.notes_margin_bottom
                )
            )
            adapter = notesAdapter
        }
    }

    private fun setupAddNewNote() {
        binding.addNoteButton.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToCreateEditNoteFragment(
                CreateEditNoteFragment.Mode.CREATE
            )
            findNavController().navigate(action)
        }
    }

}