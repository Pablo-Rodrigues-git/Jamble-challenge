package com.example.jamble_challenge.presentation

import android.app.Application
import com.example.jamble_challenge.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JambleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@JambleApplication)
            modules(appModule)
        }
    }
}