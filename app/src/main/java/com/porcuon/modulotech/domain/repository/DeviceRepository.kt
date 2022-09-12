package com.porcuon.modulotech.domain.repository

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceFilter

interface DeviceRepository {

    suspend fun getDevicesByFilters(filters: List<DeviceFilter>): List<Device>

    suspend fun saveDevices(devices: List<Device>)

    suspend fun removeDevice(device: Device): Result<Device>

    suspend fun updateDevice(device: Device): Result<Device>
}