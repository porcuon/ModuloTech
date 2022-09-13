package com.porcuon.modulotech.di

import com.porcuon.modulotech.BuildConfig
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT_SECONDS = 60L

val networkModule: Module = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single {
        val okHttpClient: OkHttpClient = get()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}