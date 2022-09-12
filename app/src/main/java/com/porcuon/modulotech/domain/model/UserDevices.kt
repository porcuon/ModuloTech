package com.porcuon.modulotech.domain.model

data class UserDevices(
    val user: User,
    val devices: List<Device>
)