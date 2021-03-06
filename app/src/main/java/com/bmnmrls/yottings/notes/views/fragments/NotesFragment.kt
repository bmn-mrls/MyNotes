package com.bmnmrls.yottings.notes.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.models.Note
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.createeditnote.views.fragments.CreateEditNoteFragment
import com.bmnmrls.yottings.databinding.FragmentNotesBinding
import com.bmnmrls.yottings.notes.adapters.NotesAdapter
import com.bmnmrls.yottings.notes.models.NotesStateEvent
import com.bmnmrls.yottings.notes.viewmodels.NotesViewModel
import com.bmnmrls.yottings.utils.VerticalSpaceItemDecoration
import com.bmnmrls.yottings.utils.ktx.hideView
import com.bmnmrls.yottings.utils.ktx.showView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels()

    @Inject
    lateinit var notesAdapter: NotesAdapter
    private lateinit var binding: FragmentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
        viewModel.setStateEvent(NotesStateEvent.GetNotes)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.setAddNoteListener { onAddNoteClicked() }
            it.errorLayout.setRetryListener { onRetryLoadNotesClicked() }
            it.executePendingBindings()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.setStateEvent(NotesStateEvent.GetNotes)
    }

    private fun setupUI() {
        setupNotes()
    }

    private fun setupNotes() {
        notesAdapter.listener = {
            val action = NotesFragmentDirections.actionNotesFragmentToCreateEditNoteFragment(
                CreateEditNoteFragment.Mode.EDIT,
                it
            )
            findNavController().navigate(action)
        }
        binding.notesRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                VerticalSpaceItemDecoration(
                    space = R.dimen.notes_vertical_space,
                    lastItemAdditionalSpace = R.dimen.notes_margin_bottom
                )
            )
            adapter = notesAdapter
        }
    }

    private fun onAddNoteClicked() {
        val action = NotesFragmentDirections.actionNotesFragmentToCreateEditNoteFragment(
            CreateEditNoteFragment.Mode.CREATE
        )
        findNavController().navigate(action)
    }

    private fun onRetryLoadNotesClicked() = viewModel.setStateEvent(NotesStateEvent.GetNotes)

    private fun loadNotes(notes: List<Note>) = notesAdapter.addNotes(notes)

    private fun initObservers() {
        viewModel.getNotesDataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Note>> -> {
                    showNotes()
                    loadNotes(dataState.data)
                }
                is DataState.Empty -> showEmpty()
                is DataState.Loading -> showProgress()
                is DataState.Error -> showError()
            }
        }
    }

    private fun showNotes() {
        binding.notesRecyclerView.showView()
        binding.addNoteButton.showView()
        binding.emptyLayout.hideView()
        binding.errorLayout.root.hideView()
        binding.loadingLayout.hideView()
    }

    private fun showProgress() {
        binding.loadingLayout.showView()
        binding.errorLayout.root.hideView()
        binding.emptyLayout.hideView()
        binding.addNoteButton.hideView()
        binding.notesRecyclerView.hideView()
    }

    private fun showError() {
        binding.errorLayout.root.showView()
        binding.loadingLayout.hideView()
        binding.emptyLayout.hideView()
        binding.addNoteButton.hideView()
        binding.notesRecyclerView.hideView()
    }

    private fun showEmpty() {
        binding.emptyLayout.showView()
        binding.addNoteButton.showView()
        binding.errorLayout.root.hideView()
        binding.loadingLayout.hideView()
        binding.notesRecyclerView.hideView()
    }

}