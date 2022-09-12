package com.porcuon.modulotech.domain.usecase

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.repository.DeviceFilterRepository
import com.porcuon.modulotech.domain.repository.DeviceRepository
import com.porcuon.modulotech.domain.preferences.UserDevicesPreferences
import com.porcuon.modulotech.domain.repository.UserDevicesRepository
import com.porcuon.modulotech.domain.repository.UserRepository
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceFilter
import com.porcuon.modulotech.domain.model.UserDevices

class GetFilteredDevicesUseCase(
    private val userDevicesRepository: UserDevicesRepository,
    private val userRepository: UserRepository,
    private val deviceRepository: DeviceRepository,
    private val deviceFilterRepository: DeviceFilterRepository,
    private val userDevicesPreferences: UserDevicesPreferences
) {

    suspend fun execute(): Result<List<Device>> {
        val shouldSyncWithNetwork: Boolean = !userDevicesPreferences.isUserDevicesStoredInDatabase()

        if (shouldSyncWithNetwork) {
            try {
                syncDataWithNetwork()
            } catch (e: Exception) {
                return Result.Error(e)
            }
        }

        return loadFromDatabase()
    }

    private suspend fun loadFromDatabase(): Result<List<Device>> {
        return try {
            val deviceFilters: List<DeviceFilter> = deviceFilterRepository.getDeviceFilters()
            val devices: List<Device> = deviceRepository.getDevicesByFilters(deviceFilters)

            Result.Success(devices)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun syncDataWithNetwork() {
        val userDevices: UserDevices = userDevicesRepository.getUserDevices()
        deviceRepository.saveDevices(userDevices.devices)
        userRepository.saveUser(userDevices.user)
        userDevicesPreferences.setUserDevicesStoredInDatabase()
    }
}