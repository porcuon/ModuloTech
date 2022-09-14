package com.porcuon.modulotech.data.network.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.DeviceType

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiDevice(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("deviceName")
    val name: String?,
    @JsonProperty("productType")
    val type: DeviceType?,
    @JsonProperty("mode")
    val mode: DeviceMode? = null,
    @JsonProperty("intensity")
    val intensity: Int? = null,
    @JsonProperty("position")
    val position: Int? = null,
    @JsonProperty("temperature")
    val temperature: Double? = null
)
