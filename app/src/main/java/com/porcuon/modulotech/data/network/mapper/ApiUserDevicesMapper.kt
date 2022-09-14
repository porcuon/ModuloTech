package com.porcuon.modulotech.data.network.mapper

import com.porcuon.modulotech.data.network.api.ApiUserDevices
import com.porcuon.modulotech.domain.model.UserDevices

class ApiUserDevicesMapper(
    private val apiDeviceMapper: ApiDeviceMapper,
    private val apiUserMapper: ApiUserMapper
) {

    fun map(apiUserDevices: ApiUserDevices) = UserDevices(
        user = apiUserMapper.map(apiUserDevices.user),
        devices = apiUserDevices.devices?.mapNotNull(apiDeviceMapper::map).orEmpty()
    )
}