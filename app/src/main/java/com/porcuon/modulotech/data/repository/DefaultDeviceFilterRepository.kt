package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.DeviceFilter
import com.porcuon.modulotech.domain.repository.DeviceFilterRepository

class DefaultDeviceFilterRepository : DeviceFilterRepository {

    private var deviceFilters: List<DeviceFilter> = listOf(
        DeviceFilter(deviceType = DeviceType.Light, isSelected = true),
        DeviceFilter(deviceType = DeviceType.Heater, isSelected = true),
        DeviceFilter(deviceType = DeviceType.RollerShutter, isSelected = true),
    )

    override fun getDeviceFilters(): List<DeviceFilter> {
        return deviceFilters
    }

    override fun updateDeviceFilters(deviceFilters: List<DeviceFilter>) {
        this.deviceFilters = deviceFilters
    }
}