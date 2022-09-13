package com.porcuon.modulotech.presentation.steering

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.repository.DeviceRepository
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter
import kotlinx.coroutines.launch

class DeviceSteeringViewModel(
    private val deviceRepository: DeviceRepository,
    private val device: Device
) : ViewModel() {

    private val _updatedDeviceLiveData = MutableLiveData<Device>()

    val updatedDeviceLiveData: LiveData<Device> = _updatedDeviceLiveData

    fun onUpdateLightClicked(
        isDeviceOn: Boolean,
        intensity: Int
    ) {
        val newLight: Light = (device as Light).copy(
            intensity = intensity,
            deviceMode = if (isDeviceOn) DeviceMode.ON else DeviceMode.OFF
        )
        updateDevice(newLight)
    }

    fun onUpdateHeaterClicked(
        isDeviceOn: Boolean,
        temperature: Double
    ) {
        val newHeater: Heater = (device as Heater).copy(
            temperature = temperature,
            deviceMode = if (isDeviceOn) DeviceMode.ON else DeviceMode.OFF
        )
        updateDevice(newHeater)
    }

    fun onUpdateRollerShutterClicked(
        position: Int
    ) {
        val newRollerShutter: RollerShutter = (device as RollerShutter).copy(
            position = position
        )
        updateDevice(newRollerShutter)
    }

    private fun updateDevice(device: Device) {
        viewModelScope.launch {
            val updateDeviceResult: Result<Device> = deviceRepository.updateDevice(device)

            when (updateDeviceResult) {
                is Result.Success -> _updatedDeviceLiveData.value = updateDeviceResult.result
                is Result.Error -> Unit
            }
        }
    }
}