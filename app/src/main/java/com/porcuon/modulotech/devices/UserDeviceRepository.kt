package com.porcuon.modulotech.devices

import com.porcuon.modulotech.utils.Response

interface UserDeviceRepository {

    suspend fun getDevicesByProductTypes(): Response<List<Device>>
}