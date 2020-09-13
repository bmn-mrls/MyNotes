package com.bmnmrls.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bmnmrls.data.local.daos.NotesDao
import com.bmnmrls.data.local.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class YottingsDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        const val DATABASE_NAME = "yottings_db"
    }

}