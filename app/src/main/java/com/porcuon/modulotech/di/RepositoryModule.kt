package com.porcuon.modulotech.di

import com.porcuon.modulotech.data.repository.DefaultDeviceFilterRepository
import com.porcuon.modulotech.data.repository.DefaultDeviceRepository
import com.porcuon.modulotech.data.repository.DefaultUserDevicesRepository
import com.porcuon.modulotech.data.repository.DefaultUserRepository
import com.porcuon.modulotech.domain.repository.DeviceFilterRepository
import com.porcuon.modulotech.domain.repository.DeviceRepository
import com.porcuon.modulotech.domain.repository.UserDevicesRepository
import com.porcuon.modulotech.domain.repository.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    single<DeviceFilterRepository> {
        DefaultDeviceFilterRepository()
    }

    factory<DeviceRepository> {
        DefaultDeviceRepository(
            deviceDao = get(),
            deviceEntityMapper = get()
        )
    }

    factory<UserDevicesRepository> {
        DefaultUserDevicesRepository(
            userDevicesNetworkSource = get(),
            apiUserDevicesMapper = get()
        )
    }

    factory<UserRepository> {
        DefaultUserRepository(
            userDao = get(),
            userEntityMapper = get()
        )
    }
}