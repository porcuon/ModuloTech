package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.data.database.dao.DeviceDao
import com.porcuon.modulotech.data.database.entity.DeviceEntity
import com.porcuon.modulotech.data.database.mapper.DeviceEntityMapper
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.repository.DeviceRepository
import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.DeviceFilter

class DefaultDeviceRepository(
    private val deviceDao: DeviceDao,
    private val deviceEntityMapper: DeviceEntityMapper
) : DeviceRepository {

    override suspend fun getDevicesByFilters(filters: List<DeviceFilter>): List<Device> {
        val appliedDeviceTypes: List<DeviceType> = filters.filter { deviceFilter ->
            deviceFilter.isSelected
        }.map { it.deviceType }
        val filteredDevices: List<DeviceEntity> = deviceDao.getDevicesByTypes(appliedDeviceTypes)

        return filteredDevices.mapNotNull(deviceEntityMapper::map)
    }

    override suspend fun saveDevices(devices: List<Device>) {
        val deviceEntities: List<DeviceEntity> = devices.map(deviceEntityMapper::map)
        deviceDao.insertAllDevices(deviceEntities)
    }

    override suspend fun removeDevice(device: Device): Result<Device> {
        return try {
            val deviceEntity: DeviceEntity = deviceEntityMapper.map(device)
            deviceDao.deleteDevice(deviceEntity)

            Result.Success(device)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateDevice(device: Device): Result<Device> {
        return try {
            val deviceEntity: DeviceEntity = deviceEntityMapper.map(device)
            deviceDao.updateDevice(deviceEntity)

            Result.Success(device)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}