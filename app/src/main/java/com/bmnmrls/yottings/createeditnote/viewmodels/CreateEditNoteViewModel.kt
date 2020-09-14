package com.bmnmrls.yottings.createeditnote.viewmodels

import android.app.Application
import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.Failure
import com.bmnmrls.domain.models.Note
import com.bmnmrls.domain.repositories.NotesRepository
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.createeditnote.models.CreateEditNoteStateEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import kotlin.random.Random

class CreateEditNoteViewModel @ViewModelInject constructor(
    app: Application,
    private val notesRepository: NotesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    val content: MutableLiveData<String> = MutableLiveData("")
    val isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)

    val createNoteDataState: LiveData<DataState<Long>> get() = _createNoteDataState

    private val _createNoteDataState = MutableLiveData<DataState<Long>>()

    fun setStateEvent(createEditNoteStateEvent: CreateEditNoteStateEvent) {
        when (createEditNoteStateEvent) {
            is CreateEditNoteStateEvent.CreateNote -> createNote()
            is CreateEditNoteStateEvent.UpdateNote -> {

            }
            is CreateEditNoteStateEvent.DeleteNote -> {

            }
            is CreateEditNoteStateEvent.None -> {
                //No-op
            }
        }
    }

    private fun createNote() {
        val context = getApplication<Application>().applicationContext
        val noteColors = getRandomColorStringPair(context)
        val contentCopy = content.value
        val isFavoriteCopy = isFavorite.value
        if (contentCopy != null && isFavoriteCopy != null) {
            val note = Note(
                content = contentCopy,
                date = Calendar.getInstance().time.toString(),
                isFavorite = isFavoriteCopy,
                firstColor = noteColors.first,
                secondColor = noteColors.second
            )
            notesRepository.createNote(note)
                .onEach { dataState ->
                    _createNoteDataState.value = dataState
                }
                .launchIn(viewModelScope)
        } else {
            _createNoteDataState.value =
                DataState.Error(Failure.GenericError(IllegalArgumentException()))
        }
    }

    companion object {
        private val colors: IntArray = intArrayOf(
            R.color.gray,
            R.color.red,
            R.color.brown,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.cyan,
            R.color.blue_light,
            R.color.blue_dark,
            R.color.purple,
            R.color.violet,
            R.color.pink
        )

        private fun getRandomColorStringPair(context: Context): Pair<String, String> {
            val firstColor = getRandomColorString(context)
            val secondColor = getRandomColorString(context)
            if (firstColor == secondColor) getRandomColorStringPair(context)
            return Pair(firstColor, secondColor)
        }

        private fun getRandomColorString(context: Context): String =
            context.getString(colors[Random.nextInt(0, colors.size - 1)])
    }

}