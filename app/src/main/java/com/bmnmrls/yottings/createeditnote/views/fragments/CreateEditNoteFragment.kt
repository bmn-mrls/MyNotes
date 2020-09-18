package com.bmnmrls.yottings.createeditnote.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bmnmrls.domain.DataState
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.createeditnote.models.CreateEditNoteStateEvent
import com.bmnmrls.yottings.createeditnote.viewmodels.CreateEditNoteViewModel
import com.bmnmrls.yottings.databinding.FragmentCreateEditNoteBinding
import com.bmnmrls.yottings.utils.ktx.hideKeyboard
import com.bmnmrls.yottings.utils.ktx.hideView
import com.bmnmrls.yottings.utils.ktx.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEditNoteFragment : Fragment() {

    private val viewModel: CreateEditNoteViewModel by viewModels()

    private val args: CreateEditNoteFragmentArgs by navArgs()
    private lateinit var binding: FragmentCreateEditNoteBinding

    enum class Mode {
        CREATE,
        EDIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditNoteBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
            it.errorLayout.setRetryListener { }
            it.executePendingBindings()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupOnBackPressed()
    }

    private fun initObservers() {
        viewModel.createNoteDataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<Long> -> {
                    showNote()
                    findNavController().popBackStack()
                }
                is DataState.Loading -> showProgress()
                is DataState.Error -> showError()
            }
        }
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    true
                }
                R.id.done -> {
                    requireActivity().hideKeyboard()
                    true
                }
                else -> false
            }
        }
    }

    private fun showNote() {
        binding.noteEditText.showView()
        binding.loadingLayout.hideView()
        binding.errorLayout.root.hideView()
    }

    private fun showProgress() {
        binding.loadingLayout.showView()
        binding.errorLayout.root.hideView()
        binding.noteEditText.hideView()
    }

    private fun showError() {
        binding.errorLayout.root.showView()
        binding.loadingLayout.hideView()
        binding.noteEditText.hideView()
    }

    private fun setupOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (args.mode == Mode.CREATE) {
                    viewModel.setStateEvent(CreateEditNoteStateEvent.CreateNote)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}