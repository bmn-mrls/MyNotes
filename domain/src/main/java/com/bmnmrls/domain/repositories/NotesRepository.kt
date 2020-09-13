package com.bmnmrls.domain.repositories

import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun createNote(note: Note): Flow<DataState<Long>>

    suspend fun updateNote(note: Note): Flow<DataState<Any>>

    suspend fun deleteNote(note: Note): Flow<DataState<Any>>

    suspend fun getNote(id: Long): Flow<DataState<Note>>

    suspend fun getNotes(): Flow<DataState<List<Note>>>

}