package com.porcuon.modulotech.presentation.profile

import com.porcuon.modulotech.domain.model.User

sealed class ProfileNavigation {
    data class OpenProfileEdit(val user: User) : ProfileNavigation()
}