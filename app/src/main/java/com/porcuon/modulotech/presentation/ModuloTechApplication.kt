package com.porcuon.modulotech.presentation

import android.app.Application
import com.porcuon.modulotech.di.initKoin

class ModuloTechApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(applicationContext)
    }
}