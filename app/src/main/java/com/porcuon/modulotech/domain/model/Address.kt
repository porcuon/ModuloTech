package com.porcuon.modulotech.domain.model

import java.io.Serializable

data class Address(
    val city: String,
    val postalCode: Int,
    val street: String,
    val streetCode: String,
    val country: String
) : Serializable
