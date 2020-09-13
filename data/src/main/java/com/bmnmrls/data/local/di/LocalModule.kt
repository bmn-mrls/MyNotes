package com.bmnmrls.data.local.di

import android.content.Context
import androidx.room.Room
import com.bmnmrls.data.local.YottingsDatabase
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
    fun provideYottingsDatabase(@ApplicationContext context: Context): YottingsDatabase {
        return Room.databaseBuilder(
            context,
            YottingsDatabase::class.java,
            YottingsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}