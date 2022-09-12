package com.porcuon.modulotech.domain.repository

import com.porcuon.modulotech.domain.model.DeviceFilter

interface DeviceFilterRepository {

    fun getDeviceFilters(): List<DeviceFilter>

    fun updateDeviceFilters(deviceFilters: List<DeviceFilter>)
}