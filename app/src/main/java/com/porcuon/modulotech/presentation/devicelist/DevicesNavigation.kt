package com.porcuon.modulotech.presentation.devicelist

import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter

sealed class DevicesNavigation {
    data class OpenLightSteering(val light: Light) : DevicesNavigation()
    data class OpenHeaterSteering(val heater: Heater) : DevicesNavigation()
    data class OpenRollerShutterSteering(val rollerShutter: RollerShutter) : DevicesNavigation()
    object OpenFilters : DevicesNavigation()
}