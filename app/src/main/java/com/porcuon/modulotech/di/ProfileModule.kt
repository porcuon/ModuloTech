package com.porcuon.modulotech.di

import com.porcuon.modulotech.data.database.ModuloTechDatabase
import com.porcuon.modulotech.data.mapper.AddressMapper
import com.porcuon.modulotech.data.mapper.UserMapper
import com.porcuon.modulotech.data.repository.DefaultUserRepository
import com.porcuon.modulotech.domain.repository.UserRepository
import com.porcuon.modulotech.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val profileModule: Module = module {

    factory {
        AddressMapper()
    }

    factory {
        UserMapper(
            addressMapper = get()
        )
    }

    factory<UserRepository> {
        DefaultUserRepository(
            userDao = get(),
            userMapper = get()
        )
    }

    single {
        val roomDatabase: ModuloTechDatabase = get()

        roomDatabase.userDao()
    }

    viewModel {
        ProfileViewModel(
            userRepository = get()
        )
    }
}