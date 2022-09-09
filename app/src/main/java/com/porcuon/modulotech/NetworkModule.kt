package com.porcuon.modulotech

import com.porcuon.modulotech.devices.UserDevicesApi
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://storage42.com/"
private const val DEFAULT_TIMEOUT_SECONDS = 60L

fun getNetworkModule(): Module = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single {
        val okHttpClient: OkHttpClient = get()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    single {
        val retrofit: Retrofit = get()

        retrofit.create(UserDevicesApi::class.java)
    }
}