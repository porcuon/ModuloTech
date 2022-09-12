package com.porcuon.modulotech.domain.repository

import com.porcuon.modulotech.domain.model.UserDevices

interface UserDevicesRepository {

    suspend fun getUserDevices(): UserDevices
}