package com.bmnmrls.yottings.createeditnote.viewmodels

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.models.Note
import com.bmnmrls.domain.repositories.NotesRepository
import com.bmnmrls.yottings.createeditnote.models.CreateEditNoteStateEvent
import com.bmnmrls.yottings.utils.ktx.getRandomColorStringPair
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

class CreateEditNoteViewModel @ViewModelInject constructor(
    app: Application,
    private val notesRepository: NotesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    private var note: Note = Note(
        content = "",
        date = Date(),
        isFavorite = false,
        firstColor = "",
        secondColor = ""
    )

    val content: MutableLiveData<String> = MutableLiveData("")
    val isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val createNoteDataState: LiveData<DataState<Long>> get() = _createNoteDataState
    val updateNoteDataState: LiveData<DataState<Any>> get() = _updateNoteDataState
    val deleteNoteDataState: LiveData<DataState<Any>> get() = _deleteNoteDataState

    private val _createNoteDataState = MutableLiveData<DataState<Long>>()
    private val _updateNoteDataState = MutableLiveData<DataState<Any>>()
    private val _deleteNoteDataState = MutableLiveData<DataState<Any>>()

    fun setStateEvent(createEditNoteStateEvent: CreateEditNoteStateEvent) {
        when (createEditNoteStateEvent) {
            is CreateEditNoteStateEvent.CreateNote -> createNote()
            is CreateEditNoteStateEvent.UpdateNote -> updateNote()
            is CreateEditNoteStateEvent.DeleteNote -> deleteNote()
            is CreateEditNoteStateEvent.None -> {
                //No-op
            }
        }
    }

    fun setOriginalNote(note: Note) {
        this.note = note
        content.value = note.content
        isFavorite.value = note.isFavorite
    }

    private fun createNote() {
        val context = getApplication<Application>().applicationContext
        val noteColors = context.getRandomColorStringPair()
        val contentCopy = content.value
        val isFavoriteCopy = isFavorite.value
        if (contentCopy != null && contentCopy.isNotEmpty() && isFavoriteCopy != null) {
            note.content = contentCopy
            note.date = Calendar.getInstance().time
            note.isFavorite = isFavoriteCopy
            note.firstColor = noteColors.first
            note.secondColor = noteColors.second
            notesRepository.createNote(note)
                .onEach { dataState ->
                    _createNoteDataState.value = dataState
                }
                .launchIn(viewModelScope)
        } else {
            _createNoteDataState.value = DataState.Empty
        }
    }

    private fun updateNote() {
        val contentCopy = content.value
        val isFavoriteCopy = isFavorite.value

        if (contentCopy == note.content && isFavoriteCopy == note.isFavorite) {
            _updateNoteDataState.value = DataState.Empty
        } else {
            if (contentCopy != null && contentCopy.isNotEmpty() && isFavoriteCopy != null) {
                note.content = contentCopy
                note.isFavorite = isFavoriteCopy
                note.date = Calendar.getInstance().time
                notesRepository.updateNote(note)
                    .onEach { dataState ->
                        _updateNoteDataState.value = dataState
                    }
                    .launchIn(viewModelScope)
            } else {
                setStateEvent(CreateEditNoteStateEvent.DeleteNote)
            }
        }
    }
    
    private fun deleteNote() {
        notesRepository.deleteNote(note)
            .onEach { dataState ->
                _deleteNoteDataState.value = dataState
            }
            .launchIn(viewModelScope)
    }

}