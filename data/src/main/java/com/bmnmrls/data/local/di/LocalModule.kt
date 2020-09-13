package com.bmnmrls.data.local.di

import android.content.Context
import androidx.room.Room
import com.bmnmrls.data.local.YottingsDatabase
import com.bmnmrls.data.local.daos.NotesDao
import com.bmnmrls.data.local.mappers.NoteEntityMapper
import com.bmnmrls.data.local.models.NoteEntity
import com.bmnmrls.data.local.repositories.NotesLocalRepository
import com.bmnmrls.domain.mappers.Transform
import com.bmnmrls.domain.models.Note
import com.bmnmrls.domain.repositories.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideYottingsDatabase(@ApplicationContext context: Context): YottingsDatabase =
        Room.databaseBuilder(
            context,
            YottingsDatabase::class.java,
            YottingsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideNotesDao(yottingsDatabase: YottingsDatabase): NotesDao = yottingsDatabase.notesDao()

    @Singleton
    @Provides
    fun provideNoteEntityMapper(): Transform<NoteEntity, Note> = NoteEntityMapper()

    @Singleton
    @Provides
    fun provideNotesLocalRepository(
        noteEntityMapper: Transform<NoteEntity, Note>,
        notesDao: NotesDao,
    ): NotesRepository = NotesLocalRepository(noteEntityMapper, notesDao)

}