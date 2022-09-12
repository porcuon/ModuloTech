package com.porcuon.modulotech.domain.model

import java.io.Serializable
import java.util.Date

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val address: Address,
    val dateOfBirth: Date
) : Serializable