package com.bmnmrls.yottings.notes.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.models.Note
import com.bmnmrls.domain.repositories.NotesRepository
import com.bmnmrls.yottings.notes.models.NotesStateEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NotesViewModel @ViewModelInject constructor(
    private val notesRepository: NotesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _getNotesDataState = MutableLiveData<DataState<List<Note>>>()
    val getNotesDataState: LiveData<DataState<List<Note>>> get() = _getNotesDataState

    fun setStateEvent(notesStateEvent: NotesStateEvent) {
        when (notesStateEvent) {
            is NotesStateEvent.GetNotes -> {
                notesRepository.getNotes()
                    .onEach { dataState ->
                        if (dataState is DataState.Success) {
                            val notesSortedByDate = sortNotesByDate(dataState.data)
                            _getNotesDataState.value = DataState.Success(notesSortedByDate)
                        } else {
                            _getNotesDataState.value = dataState
                        }
                    }
                    .launchIn(viewModelScope)
            }
            is NotesStateEvent.None -> {
                //No-op
            }
        }
    }

    private fun sortNotesByDate(notes: List<Note>): List<Note> =
        notes.sortedByDescending { it.date }

}