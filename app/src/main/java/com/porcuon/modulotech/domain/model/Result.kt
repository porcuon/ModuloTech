package com.porcuon.modulotech.domain.model

sealed class Result<out R> {
    data class Success<R>(val result: R): Result<R>()
    data class Error(val error: Exception): Result<Nothing>()
}