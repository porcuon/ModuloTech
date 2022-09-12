package com.porcuon.modulotech.di

import com.porcuon.modulotech.data.repository.DefaultDeviceFilterRepository
import com.porcuon.modulotech.domain.repository.DeviceFilterRepository
import com.porcuon.modulotech.presentation.filters.DeviceFiltersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val deviceFiltersModule: Module = module {

    single<DeviceFilterRepository> {
        DefaultDeviceFilterRepository()
    }

    viewModel {
        DeviceFiltersViewModel(
            deviceFilterRepository = get()
        )
    }
}