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
    // Discuss possibility of changing this field to string in some date format (e.g. dd/mm/yyyy)
    // Keeping it as long may be more difficult to debug and lead to inconsistency
    // across other clients (web/iOS/android)
    @JsonProperty("birthDate")
    val dateOfBirth: Long?
)