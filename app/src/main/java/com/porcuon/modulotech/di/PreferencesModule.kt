package com.porcuon.modulotech.di

import com.porcuon.modulotech.data.preferences.DefaultUserDevicesPreferences
import com.porcuon.modulotech.domain.preferences.UserDevicesPreferences
import org.koin.core.module.Module
import org.koin.dsl.module

val preferencesModule: Module = module {

    single<UserDevicesPreferences> {
        DefaultUserDevicesPreferences(
            context = get()
        )
    }
}