package com.porcuon.modulotech.di

import com.porcuon.modulotech.data.database.ModuloTechDatabase
import com.porcuon.modulotech.data.mapper.DeviceMapper
import com.porcuon.modulotech.data.repository.DefaultDeviceRepository
import com.porcuon.modulotech.domain.repository.DeviceRepository
import com.porcuon.modulotech.presentation.devicelist.DevicesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val devicesModule: Module = module {

    factory<DeviceRepository> {
        DefaultDeviceRepository(
            deviceDao = get(),
            deviceMapper = get()
        )
    }

    single {
        val roomDatabase: ModuloTechDatabase = get()

        roomDatabase.deviceDao()
    }

    factory {
        DeviceMapper()
    }

    viewModel {
        DevicesViewModel(
            getFilteredDevicesUseCase = get(),
            deviceRepository = get()
        )
    }
}