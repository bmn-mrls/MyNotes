package com.bmnmrls.domain.repositories

import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun createNote(note: Note): Flow<DataState<Long>>

    fun updateNote(note: Note): Flow<DataState<Any>>

    fun deleteNote(note: Note): Flow<DataState<Any>>

    fun getNote(id: Long): Flow<DataState<Note>>

    fun getNotes(): Flow<DataState<List<Note>>>

}