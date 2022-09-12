package com.porcuon.modulotech.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date

fun Date.format(pattern: String): String {
    val dateFormatter = SimpleDateFormat(pattern)
    return dateFormatter.format(this)
}

fun getDateFromString(text: String, pattern: String): Date {
    val dateFormatter = SimpleDateFormat(pattern)
    return dateFormatter.parse(text) ?: Date()
}