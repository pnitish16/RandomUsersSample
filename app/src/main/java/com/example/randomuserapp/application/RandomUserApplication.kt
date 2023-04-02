package com.example.randomuserapp.application

import android.app.Application
import com.example.randomuserapp.di.applicationModule
import com.example.randomuserapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RandomUserApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@RandomUserApplication)
            modules(listOf(applicationModule, presentationModule))
        }
    }
}