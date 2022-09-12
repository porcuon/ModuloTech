package com.porcuon.modulotech.data.mapper

import com.porcuon.modulotech.data.entity.DeviceEntity
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter

private const val NO_ID = -1L

class DeviceMapper {

    fun map(deviceEntity: DeviceEntity): Device? {
        return when (deviceEntity.type) {
            DeviceType.Light -> createLight(deviceEntity)
            DeviceType.Heater -> createHeater(deviceEntity)
            DeviceType.RollerShutter -> createRollerShutter(deviceEntity)
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

    private fun createLight(deviceEntity: DeviceEntity): Device = Light(
        id = deviceEntity.id ?: NO_ID,
        name = deviceEntity.name.orEmpty(),
        intensity = deviceEntity.intensity ?: Light.MIN_INTENSITY,
        deviceMode = deviceEntity.mode ?: DeviceMode.OFF
    )

    private fun createHeater(deviceEntity: DeviceEntity): Device = Heater(
        id = deviceEntity.id ?: NO_ID,
        name = deviceEntity.name.orEmpty(),
        temperature = deviceEntity.temperature ?: Heater.MIN_TEMPERATURE,
        deviceMode = deviceEntity.mode ?: DeviceMode.OFF
    )

    private fun createRollerShutter(deviceEntity: DeviceEntity): Device = RollerShutter(
        id = deviceEntity.id ?: NO_ID,
        name = deviceEntity.name.orEmpty(),
        position = deviceEntity.position ?: RollerShutter.MIN_POSITION
    )
}