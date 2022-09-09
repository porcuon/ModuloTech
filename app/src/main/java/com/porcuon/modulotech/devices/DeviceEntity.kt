package com.porcuon.modulotech.devices

import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "device")
@JsonIgnoreProperties(ignoreUnknown = true)
data class DeviceEntity(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("deviceName")
    val name: String?,
    @JsonProperty("productType")
    val productType: String?,
    @JsonProperty("mode")
    val mode: String?,
    @JsonProperty("intensity")
    val intensity: Int?,
    @JsonProperty("position")
    val position: Int?,
    @JsonProperty("temperature")
    val temperature: Double?
)