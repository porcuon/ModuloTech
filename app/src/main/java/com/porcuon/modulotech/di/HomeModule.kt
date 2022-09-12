package com.porcuon.modulotech.di

import com.porcuon.modulotech.data.repository.DefaultUserDevicesRepository
import com.porcuon.modulotech.data.network.api.UserDevicesApi
import com.porcuon.modulotech.data.mapper.UserDevicesMapper
import com.porcuon.modulotech.data.preferences.DefaultUserDevicesPreferences
import com.porcuon.modulotech.domain.usecase.GetFilteredDevicesUseCase
import com.porcuon.modulotech.domain.preferences.UserDevicesPreferences
import com.porcuon.modulotech.domain.repository.UserDevicesRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule: Module = module {

    factory<UserDevicesRepository> {
        DefaultUserDevicesRepository(
            userDevicesApi = get(),
            userDevicesMapper = get()
        )
    }

    factory {
        UserDevicesMapper(
            deviceMapper = get(),
            userMapper = get()
        )
    }

    factory {
        GetFilteredDevicesUseCase(
            userDevicesRepository = get(),
            userRepository = get(),
            deviceRepository = get(),
            deviceFilterRepository = get(),
            userDevicesPreferences = get()
        )
    }

    single {
        val retrofit: Retrofit = get()

        retrofit.create(UserDevicesApi::class.java)
    }

    single<UserDevicesPreferences> {
        DefaultUserDevicesPreferences(
            context = get()
        )
    }
}