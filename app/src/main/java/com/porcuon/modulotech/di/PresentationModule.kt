package com.porcuon.modulotech.di

import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.presentation.devicelist.DevicesViewModel
import com.porcuon.modulotech.presentation.filters.DeviceFiltersViewModel
import com.porcuon.modulotech.presentation.profile.ProfileViewModel
import com.porcuon.modulotech.presentation.profileedit.ProfileEditViewModel
import com.porcuon.modulotech.presentation.steering.DeviceSteeringViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule: Module = module {

    viewModel {
        DevicesViewModel(
            getFilteredDevicesUseCase = get(),
            deviceRepository = get()
        )
    }

    viewModel {
        DeviceFiltersViewModel(
            deviceFilterRepository = get()
        )
    }

    viewModel {
        ProfileEditViewModel(
            userRepository = get()
        )
    }

    viewModel {
        ProfileViewModel(
            userRepository = get()
        )
    }

    viewModel { (device: Device) ->
        DeviceSteeringViewModel(
            deviceRepository = get(),
            device = device
        )
    }
}