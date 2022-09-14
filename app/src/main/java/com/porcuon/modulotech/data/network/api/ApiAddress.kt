package com.porcuon.modulotech.data.network.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiAddress(
    @JsonProperty("city")
    val city: String?,
    @JsonProperty("postalCode")
    val postalCode: Int?,
    @JsonProperty("street")
    val street: String?,
    @JsonProperty("streetCode")
    val streetCode: String?,
    @JsonProperty("country")
    val country: String?
)