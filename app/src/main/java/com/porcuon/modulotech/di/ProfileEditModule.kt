package com.porcuon.modulotech.di

import com.porcuon.modulotech.presentation.profileedit.ProfileEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val profileEditModule: Module = module {
    viewModel {
        ProfileEditViewModel(
            userRepository = get()
        )
    }
}