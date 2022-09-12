package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.data.database.dao.DeviceDao
import com.porcuon.modulotech.data.entity.DeviceEntity
import com.porcuon.modulotech.data.mapper.DeviceMapper
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.repository.DeviceRepository
import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.DeviceFilter

class DefaultDeviceRepository(
    private val deviceDao: DeviceDao,
    private val deviceMapper: DeviceMapper
) : DeviceRepository {

    override suspend fun getDevicesByFilters(filters: List<DeviceFilter>): List<Device> {
        val appliedDeviceTypes: List<DeviceType> = filters.filter { deviceFilter ->
            deviceFilter.isSelected
        }.map { it.deviceType }
        val filteredDevices: List<DeviceEntity> = deviceDao.getDevicesByTypes(appliedDeviceTypes)

        return filteredDevices.mapNotNull(deviceMapper::map)
    }

    override suspend fun saveDevices(devices: List<Device>) {
        val deviceEntities: List<DeviceEntity> = devices.map(deviceMapper::map)
        deviceDao.insertAllDevices(deviceEntities)
    }

    override suspend fun removeDevice(device: Device): Result<Device> {
        return try {
            val deviceEntity: DeviceEntity = deviceMapper.map(device)
            deviceDao.deleteDevice(deviceEntity)

            Result.Success(device)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateDevice(device: Device): Result<Device> {
        return try {
            val deviceEntity: DeviceEntity = deviceMapper.map(device)
            deviceDao.updateDevice(deviceEntity)

            Result.Success(device)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}