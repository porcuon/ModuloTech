package com.porcuon.modulotech.data.network.source

import com.porcuon.modulotech.data.network.api.ApiUserDevices
import retrofit2.Response
import retrofit2.http.GET

interface UserDevicesNetworkSource {

    @GET("modulotest/data.json")
    suspend fun getUserDevices(): Response<ApiUserDevices>
}