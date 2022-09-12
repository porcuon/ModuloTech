package com.porcuon.modulotech.data.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDevicesEntity(
    @JsonProperty("user")
    val user: UserEntity?,
    @JsonProperty("devices")
    val devices: List<DeviceEntity>?
)