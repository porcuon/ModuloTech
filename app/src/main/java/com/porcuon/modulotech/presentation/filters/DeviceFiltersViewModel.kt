package com.porcuon.modulotech.presentation.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.porcuon.modulotech.domain.model.DeviceFilter
import com.porcuon.modulotech.domain.repository.DeviceFilterRepository

class DeviceFiltersViewModel(
    private val deviceFilterRepository: DeviceFilterRepository
) : ViewModel() {

    private val deviceFiltersLiveData = MutableLiveData<List<DeviceFilter>>()

    init {
        deviceFiltersLiveData.value = deviceFilterRepository.getDeviceFilters()
    }

    fun getDeviceFiltersLiveData(): LiveData<List<DeviceFilter>> = deviceFiltersLiveData

    fun onDeviceFilterClicked(deviceFilter: DeviceFilter) {
        deviceFilter.isSelected = !deviceFilter.isSelected
    }

    fun onDestroy() {
        val deviceFilters: List<DeviceFilter> = deviceFiltersLiveData.value ?: return
        deviceFilterRepository.updateDeviceFilters(deviceFilters)
    }
}