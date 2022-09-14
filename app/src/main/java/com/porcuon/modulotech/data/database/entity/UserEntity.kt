package com.porcuon.modulotech.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("firstName")
    val firstName: String?,
    @JsonProperty("lastName")
    val lastName: String?,
    @Embedded
    @JsonProperty("address")
    val address: AddressEntity?,
    @JsonProperty("birthDate")
    val dateOfBirth: Long?
)