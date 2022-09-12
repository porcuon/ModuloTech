package com.porcuon.modulotech.presentation.devicelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.usecase.GetFilteredDevicesUseCase
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter
import com.porcuon.modulotech.presentation.utils.Event
import com.porcuon.modulotech.domain.repository.DeviceRepository
import kotlinx.coroutines.launch

class DevicesViewModel(
    private val getFilteredDevicesUseCase: GetFilteredDevicesUseCase,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    private val navigationLiveData = MutableLiveData<Event<DevicesNavigation>>()
    private val devicesLiveData = MutableLiveData<List<Device>>()

    init {
        loadDevices()
    }

    fun getDevicesLiveData(): LiveData<List<Device>> = devicesLiveData
    fun getNavigationLiveData(): LiveData<Event<DevicesNavigation>> = navigationLiveData

    fun onDeviceClicked(device: Device) {
        when (device) {
            is Light -> navigationLiveData.value = Event(DevicesNavigation.OpenLightSteering(device))
            is Heater -> navigationLiveData.value = Event(DevicesNavigation.OpenHeaterSteering(device))
            is RollerShutter -> navigationLiveData.value = Event(DevicesNavigation.OpenRollerShutterSteering(device))
        }
    }

    fun onRemoveButtonClicked(device: Device) {
        viewModelScope.launch {
            val removeDeviceResult: Result<Device> = deviceRepository.removeDevice(device)

            when (removeDeviceResult) {
                is Result.Success -> handleRemoveDeviceSuccess(removeDeviceResult.result)
                is Result.Error -> Unit
            }
        }
    }

    fun onFilterClicked() {
        navigationLiveData.value = Event(DevicesNavigation.OpenFilters)
    }

    fun onFiltersApplied() {
        loadDevices()
    }

    fun onDeviceUpdated(updatedDevice: Device) {
        val currentDevices: MutableList<Device> = devicesLiveData.value?.toMutableList() ?: return
        val updatedDeviceIndex: Int = currentDevices.indexOfFirst { it.id == updatedDevice.id }

        if (updatedDeviceIndex != -1) {
            currentDevices[updatedDeviceIndex] = updatedDevice
            devicesLiveData.value = currentDevices
        }
    }

    private fun loadDevices() {
        viewModelScope.launch {
            val devicesResult: Result<List<Device>> = getFilteredDevicesUseCase.execute()

            when (devicesResult) {
                is Result.Success -> devicesLiveData.value = devicesResult.result
                is Result.Error -> Unit
            }
        }
    }

    private fun handleRemoveDeviceSuccess(device: Device) {
        val currentDevices: List<Device> = devicesLiveData.value ?: return
        val devicesCopy = ArrayList(currentDevices)

        devicesCopy.remove(device)
        devicesLiveData.value = devicesCopy
    }
}