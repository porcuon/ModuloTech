package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.data.network.source.UserDevicesNetworkSource
import com.porcuon.modulotech.data.network.api.ApiUserDevices
import com.porcuon.modulotech.data.network.mapper.ApiUserDevicesMapper
import com.porcuon.modulotech.domain.model.UserDevices
import com.porcuon.modulotech.domain.repository.UserDevicesRepository
import com.porcuon.modulotech.data.network.utils.unwrap

class DefaultUserDevicesRepository(
    private val userDevicesNetworkSource: UserDevicesNetworkSource,
    private val apiUserDevicesMapper: ApiUserDevicesMapper
) : UserDevicesRepository {

    override suspend fun getUserDevices(): UserDevices {
        val apiUserDevices: ApiUserDevices = userDevicesNetworkSource.getUserDevices().unwrap()

        return apiUserDevicesMapper.map(apiUserDevices)
    }
}