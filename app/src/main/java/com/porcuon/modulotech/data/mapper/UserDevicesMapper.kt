package com.porcuon.modulotech.data.mapper

import com.porcuon.modulotech.data.entity.UserDevicesEntity
import com.porcuon.modulotech.domain.model.UserDevices

class UserDevicesMapper(
    private val deviceMapper: DeviceMapper,
    private val userMapper: UserMapper
) {

    fun map(userDevicesEntity: UserDevicesEntity): UserDevices = UserDevices(
        user = userMapper.map(userDevicesEntity.user),
        devices = userDevicesEntity.devices?.mapNotNull(deviceMapper::map).orEmpty()
    )
}