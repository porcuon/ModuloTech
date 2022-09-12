package com.porcuon.modulotech.di

import android.content.Context
import androidx.room.Room
import com.porcuon.modulotech.data.database.ModuloTechDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private const val DATABASE_NAME = "module_tech_db"

val databaseModule: Module = module {

    single {
        val context: Context = androidContext()

        Room.databaseBuilder(context, ModuloTechDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}