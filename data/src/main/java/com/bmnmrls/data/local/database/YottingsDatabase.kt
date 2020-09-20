package com.bmnmrls.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bmnmrls.data.local.converters.Converters
import com.bmnmrls.data.local.daos.NotesDao
import com.bmnmrls.data.local.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class YottingsDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        const val DATABASE_NAME = "yottings_db"
    }

}