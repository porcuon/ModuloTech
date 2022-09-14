package com.porcuon.modulotech.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.DeviceType

@Entity(tableName = "device")
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String?,
    val type: DeviceType?,
    val mode: DeviceMode? = null,
    val intensity: Int? = null,
    val position: Int? = null,
    val temperature: Double? = null
)