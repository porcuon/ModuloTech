package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.data.network.api.UserDevicesApi
import com.porcuon.modulotech.data.entity.UserDevicesEntity
import com.porcuon.modulotech.data.mapper.UserDevicesMapper
import com.porcuon.modulotech.domain.model.UserDevices
import com.porcuon.modulotech.domain.repository.UserDevicesRepository
import com.porcuon.modulotech.data.network.utils.unwrap

class DefaultUserDevicesRepository(
    private val userDevicesApi: UserDevicesApi,
    private val userDevicesMapper: UserDevicesMapper
) : UserDevicesRepository {

    override suspend fun getUserDevices(): UserDevices {
        val userDevicesEntity: UserDevicesEntity = userDevicesApi.getUserDevices().unwrap()

        return userDevicesMapper.map(userDevicesEntity)
    }
}