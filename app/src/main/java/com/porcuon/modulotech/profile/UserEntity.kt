package com.porcuon.modulotech.profile

import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
data class UserEntity(
    @JsonProperty("firstName")
    val firstName: String?,
    @JsonProperty("lastName")
    val lastName: String?,
    @JsonProperty("address")
    val address: AddressEntity?,
    @JsonProperty("birthDate")
    val dateOfBirth: Long?
)