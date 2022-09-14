package com.porcuon.modulotech.data.network.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiUserDevices(
    @JsonProperty("user")
    val user: ApiUser?,
    @JsonProperty("devices")
    val devices: List<ApiDevice>?
)