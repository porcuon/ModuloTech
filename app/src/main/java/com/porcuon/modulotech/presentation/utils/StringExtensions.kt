package com.porcuon.modulotech.presentation.utils

fun Any?.toStringOrEmpty(): String {
    return if (this.toString() == "null") "" else this.toString()
}