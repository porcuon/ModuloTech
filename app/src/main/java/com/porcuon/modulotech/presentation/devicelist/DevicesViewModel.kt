package com.porcuon.modulotech.presentation.devicelist

import android.annotation.SuppressLint
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

    private val _navigationLiveData = MutableLiveData<Event<DevicesNavigation>>()
    private val _devicesLiveData = MutableLiveData<List<Device>>()

    val navigationLiveData: LiveData<Event<DevicesNavigation>> = _navigationLiveData
    val devicesLiveData: LiveData<List<Device>> = _devicesLiveData

    init {
        loadDevices()
    }

    fun onDeviceClicked(device: Device) {
        when (device) {
            is Light -> _navigationLiveData.value = Event(DevicesNavigation.OpenLightSteering(device))
            is Heater -> _navigationLiveData.value = Event(DevicesNavigation.OpenHeaterSteering(device))
            is RollerShutter -> _navigationLiveData.value = Event(DevicesNavigation.OpenRollerShutterSteering(device))
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
        _navigationLiveData.value = Event(DevicesNavigation.OpenFilters)
    }

    fun onFiltersApplied() {
        loadDevices()
    }

    fun onDeviceUpdated(updatedDevice: Device) {
        val currentDevices: MutableList<Device> = _devicesLiveData.value?.toMutableList() ?: return
        val updatedDeviceIndex: Int = currentDevices.indexOfFirst { it.id == updatedDevice.id }

        if (updatedDeviceIndex != -1) {
            currentDevices[updatedDeviceIndex] = updatedDevice
            _devicesLiveData.value = currentDevices
        }
    }

    private fun loadDevices() {
        viewModelScope.launch {
            val devicesResult: Result<List<Device>> = getFilteredDevicesUseCase.execute()

            @SuppressLint("NullSafeMutableLiveData")
            when (devicesResult) {
                is Result.Success -> _devicesLiveData.value = devicesResult.result
                is Result.Error -> Unit
            }
        }
    }

    private fun handleRemoveDeviceSuccess(device: Device) {
        val currentDevices: List<Device> = _devicesLiveData.value ?: return
        val devicesCopy = ArrayList(currentDevices)

        devicesCopy.remove(device)
        _devicesLiveData.value = devicesCopy
    }
}