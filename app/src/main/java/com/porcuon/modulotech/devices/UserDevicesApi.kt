package com.porcuon.modulotech.devices

import retrofit2.http.GET

interface UserDevicesApi {

    @GET("modulotest/data.json")
    suspend fun getUserDevices(): UserDevicesEntity
}