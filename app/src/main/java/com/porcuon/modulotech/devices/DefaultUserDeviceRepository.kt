package com.porcuon.modulotech.devices

import com.porcuon.modulotech.utils.Response

class DefaultUserDeviceRepository(
    private val userDevicesApi: UserDevicesApi
) : UserDeviceRepository {

    override suspend fun getDevicesByProductTypes(): Response<List<Device>> {
        return try {
            Response.Success(listOf())
        } catch (exception: Exception) {
            Response.Error(exception)
        }
    }
}