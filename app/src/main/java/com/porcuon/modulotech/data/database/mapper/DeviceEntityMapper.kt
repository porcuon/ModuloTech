package com.porcuon.modulotech.data.database.mapper

import com.porcuon.modulotech.data.database.entity.DeviceEntity
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter

class DeviceEntityMapper {

    fun map(deviceEntity: DeviceEntity): Device? {
        return when (deviceEntity.type) {
            DeviceType.Light -> Light(
                id = deviceEntity.id ?: -1,
                name = deviceEntity.name.orEmpty(),
                intensity = deviceEntity.intensity ?: Light.MIN_INTENSITY,
                deviceMode = deviceEntity.mode ?: DeviceMode.OFF
            )
            DeviceType.Heater -> Heater(
                id = deviceEntity.id ?: -1,
                name = deviceEntity.name.orEmpty(),
                temperature = deviceEntity.temperature ?: Heater.MIN_TEMPERATURE,
                deviceMode = deviceEntity.mode ?: DeviceMode.OFF
            )
            DeviceType.RollerShutter -> RollerShutter(
                id = deviceEntity.id ?: -1,
                name = deviceEntity.name.orEmpty(),
                position = deviceEntity.position ?: RollerShutter.MIN_POSITION
            )
            else -> null
        }
    }

    fun map(device: Device): DeviceEntity {
        return when (device) {
            is Light -> DeviceEntity(
                id = device.id,
                name = device.name,
                type = DeviceType.Light,
                intensity = device.intensity,
                mode = device.deviceMode
            )
            is Heater -> DeviceEntity(
                id = device.id,
                name = device.name,
                type = DeviceType.Heater,
                temperature = device.temperature,
                mode = device.deviceMode
            )
            is RollerShutter -> DeviceEntity(
                id = device.id,
                name = device.name,
                type = DeviceType.RollerShutter,
                position = device.position
            )
        }
    }
}