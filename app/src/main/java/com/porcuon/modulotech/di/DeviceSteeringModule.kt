package com.porcuon.modulotech.di

import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.presentation.steering.DeviceSteeringViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val deviceSteeringModule: Module = module {
    viewModel { (device: Device) ->
        DeviceSteeringViewModel(
            deviceRepository = get(),
            device = device
        )
    }
}