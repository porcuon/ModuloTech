package com.porcuon.modulotech.data.network.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiUser(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("firstName")
    val firstName: String?,
    @JsonProperty("lastName")
    val lastName: String?,
    @JsonProperty("address")
    val address: ApiAddress?,
    @JsonProperty("birthDate")
    val dateOfBirth: Long?
)
