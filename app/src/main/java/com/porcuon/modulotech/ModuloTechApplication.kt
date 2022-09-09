package com.porcuon.modulotech

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ModuloTechApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(applicationContext)
    }
}