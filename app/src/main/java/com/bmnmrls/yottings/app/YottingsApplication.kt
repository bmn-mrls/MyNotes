package com.bmnmrls.yottings.app

import android.app.Application
import com.bmnmrls.yottings.BuildConfig
import timber.log.Timber

class YottingsApplication : Application() {

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