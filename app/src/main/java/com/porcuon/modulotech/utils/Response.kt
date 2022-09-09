package com.porcuon.modulotech.utils

sealed class Response<out Result> {
    data class Success<Result>(val result: Result): Response<Result>()
    data class Error(val error: Exception): Response<Nothing>()
}