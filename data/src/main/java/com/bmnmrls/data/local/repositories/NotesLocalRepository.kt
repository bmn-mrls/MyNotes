package com.bmnmrls.data.local.repositories

import com.bmnmrls.data.local.daos.NotesDao
import com.bmnmrls.data.local.models.NoteEntity
import com.bmnmrls.domain.DataState
import com.bmnmrls.domain.Failure
import com.bmnmrls.domain.mappers.Transform
import com.bmnmrls.domain.models.Note
import com.bmnmrls.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesLocalRepository @Inject constructor(
    private val noteEntityMapper: Transform<NoteEntity, Note>,
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun createNote(note: Note): Flow<DataState<Long>> = flow {
        emit(DataState.Loading)
        try {
            val noteEntity = noteEntityMapper.reverseTransform(note)
            val id = notesDao.insertNote(noteEntity)
            emit(DataState.Success(id))
        } catch (e: Exception) {
            emit(DataState.Error(Failure.GenericError(e)))
        }
    }

    override suspend fun updateNote(note: Note): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        try {
            val noteEntity = noteEntityMapper.reverseTransform(note)
            notesDao.updateNote(noteEntity)
            emit(DataState.Success(Any()))
        } catch (e: Exception) {
            emit(DataState.Error(Failure.GenericError(e)))
        }
    }

    override suspend fun deleteNote(note: Note): Flow<DataState<Any>> = flow {
        emit(DataState.Loading)
        try {
            val noteEntity = noteEntityMapper.reverseTransform(note)
            notesDao.deleteNote(noteEntity)
            emit(DataState.Success(Any()))
        } catch (e: Exception) {
            emit(DataState.Error(Failure.GenericError(e)))
        }
    }

    override suspend fun getNote(id: Long): Flow<DataState<Note>> = flow {
        emit(DataState.Loading)
        try {
            val note = noteEntityMapper.transform(notesDao.getNote(id))
            emit(DataState.Success(note))
        } catch (e: Exception) {
            emit(DataState.Error(Failure.GenericError(e)))
        }
    }

    override suspend fun getNotes(): Flow<DataState<List<Note>>> = flow {
        emit(DataState.Loading)
        try {
            val notes = noteEntityMapper.transformCollection(notesDao.getNotes())
            emit(if (notes.isEmpty()) DataState.Empty else DataState.Success(notes))
        } catch (e: Exception) {
            emit(DataState.Error(Failure.GenericError(e)))
        }
    }

}