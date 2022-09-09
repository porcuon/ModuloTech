package com.porcuon.modulotech.devices

sealed class Device {
    abstract val id: Long
    abstract val name: String
}

data class Light(
    override val id: Long,
    override val name: String,
    val intensity: Int,
    val deviceMode: DeviceMode
) : Device()

data class Heater(
    override val id: Long,
    override val name: String,
    val temperature: Double,
    val deviceMode: DeviceMode
) : Device()

data class RollerShutter(
    override val id: Long,
    override val name: String,
    val position: Int
) : Device()