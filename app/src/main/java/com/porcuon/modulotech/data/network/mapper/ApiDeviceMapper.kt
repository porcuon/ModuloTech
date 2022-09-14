package com.porcuon.modulotech.data.network.mapper

import com.porcuon.modulotech.data.network.api.ApiDevice
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter

private const val NO_ID = -1L

class ApiDeviceMapper {

    fun map(apiDevice: ApiDevice): Device? {
        return when (apiDevice.type) {
            DeviceType.Light -> Light(
                id = apiDevice.id ?: NO_ID,
                name = apiDevice.name.orEmpty(),
                intensity = apiDevice.intensity ?: Light.MIN_INTENSITY,
                deviceMode = apiDevice.mode ?: DeviceMode.OFF
            )
            DeviceType.Heater -> Heater(
                id = apiDevice.id ?: NO_ID,
                name = apiDevice.name.orEmpty(),
                temperature = apiDevice.temperature ?: Heater.MIN_TEMPERATURE,
                deviceMode = apiDevice.mode ?: DeviceMode.OFF
            )
            DeviceType.RollerShutter -> RollerShutter(
                id = apiDevice.id ?: NO_ID,
                name = apiDevice.name.orEmpty(),
                position = apiDevice.position ?: RollerShutter.MIN_POSITION
            )
            else -> null
        }
    }
}