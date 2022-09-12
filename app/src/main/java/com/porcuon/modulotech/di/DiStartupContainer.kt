package com.porcuon.modulotech.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(getModules())
    }
}

private fun getModules(): List<Module> = listOf(
    networkModule,
    databaseModule,
    homeModule,
    devicesModule,
    profileModule,
    deviceFiltersModule,
    deviceSteeringModule,
    profileEditModule
)