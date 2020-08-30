package com.bmnmrls.mynotes.app

import android.app.Application
import com.bmnmrls.mynotes.BuildConfig
import timber.log.Timber

class MyNotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLogger()
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}