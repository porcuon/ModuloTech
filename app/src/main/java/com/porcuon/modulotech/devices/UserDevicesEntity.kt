package com.porcuon.modulotech.devices

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.porcuon.modulotech.profile.UserEntity

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDevicesEntity(
    @JsonProperty("user")
    val user: UserEntity?,
    @JsonProperty("devices")
    val devices: List<DeviceEntity>?
)