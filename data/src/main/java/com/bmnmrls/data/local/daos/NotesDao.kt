package com.bmnmrls.data.local.daos

import androidx.room.*
import com.bmnmrls.data.local.models.NoteEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id =:id")
    suspend fun getNote(id: Long): NoteEntity

    @Query("SELECT * FROM notes")
    suspend fun getNotes(): List<NoteEntity>

}