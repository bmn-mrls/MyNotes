package com.bmnmrls.yottings.notes.models

sealed class NotesStateEvent {

    object GetNotes : NotesStateEvent()

    object None : NotesStateEvent()

}