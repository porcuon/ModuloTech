package com.porcuon.modulotech

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDatabaseModule(): Module = module {

    single {
        val context: Context = androidContext()

        Room.databaseBuilder(context, ModuloTechDatabase::class.java, "module_tech_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        val roomDatabase: ModuloTechDatabase = get()

        roomDatabase.deviceDao()
    }

    single {
        val roomDatabase: ModuloTechDatabase = get()

        roomDatabase.userDao()
    }
}