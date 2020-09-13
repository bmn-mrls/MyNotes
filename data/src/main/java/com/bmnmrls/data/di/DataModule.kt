package com.bmnmrls.data.di

import com.bmnmrls.data.local.daos.NotesDao
import com.bmnmrls.data.local.models.NoteEntity
import com.bmnmrls.data.repositories.NotesDataRepository
import com.bmnmrls.domain.mappers.Transform
import com.bmnmrls.domain.models.Note
import com.bmnmrls.domain.repositories.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideNotesDataRepository(
        noteEntityMapper: Transform<NoteEntity, Note>,
        notesDao: NotesDao,
    ): NotesRepository = NotesDataRepository(noteEntityMapper, notesDao)

}