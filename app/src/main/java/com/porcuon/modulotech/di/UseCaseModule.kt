package com.porcuon.modulotech.di

import com.porcuon.modulotech.domain.usecase.GetFilteredDevicesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {

    factory {
        GetFilteredDevicesUseCase(
            userDevicesRepository = get(),
            userRepository = get(),
            deviceRepository = get(),
            deviceFilterRepository = get(),
            userDevicesPreferences = get()
        )
    }
}