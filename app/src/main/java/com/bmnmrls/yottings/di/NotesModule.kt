package com.bmnmrls.yottings.di

import com.bmnmrls.yottings.notes.adapters.NotesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NotesModule {

    @Provides
    fun provideNotesAdapter(): NotesAdapter = NotesAdapter()

}