package com.porcuon.modulotech.data.network.utils

import retrofit2.Response

fun <T> Response<T>.unwrap(): T {
    val response = this

    return when {
        !response.isSuccessful -> throw Exception()
        response.body() != null -> response.body()!!
        else -> throw Exception()
    }
}