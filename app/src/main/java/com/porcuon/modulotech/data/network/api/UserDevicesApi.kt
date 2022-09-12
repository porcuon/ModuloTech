package com.porcuon.modulotech.data.network.api

import com.porcuon.modulotech.data.entity.UserDevicesEntity
import retrofit2.Response
import retrofit2.http.GET

interface UserDevicesApi {

    @GET("modulotest/data.json")
    suspend fun getUserDevices(): Response<UserDevicesEntity>
}