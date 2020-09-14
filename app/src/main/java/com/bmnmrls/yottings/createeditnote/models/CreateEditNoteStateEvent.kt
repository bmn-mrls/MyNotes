package com.bmnmrls.yottings.createeditnote.models

sealed class CreateEditNoteStateEvent {

    object CreateNote : CreateEditNoteStateEvent()

    object UpdateNote : CreateEditNoteStateEvent()

    object DeleteNote : CreateEditNoteStateEvent()

    object None : CreateEditNoteStateEvent()

}