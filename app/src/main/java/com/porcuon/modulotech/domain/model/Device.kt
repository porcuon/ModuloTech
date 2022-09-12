package com.porcuon.modulotech.domain.model

import java.io.Serializable

sealed class Device : Serializable {
    abstract val id: Long
    abstract val name: String
}

data class Light(
    override val id: Long,
    override val name: String,
    val intensity: Int,
    val deviceMode: DeviceMode
) : Device() {

    companion object {
        const val MIN_INTENSITY = 0
        const val MAX_INTENSITY = 100
    }
}

data class Heater(
    override val id: Long,
    override val name: String,
    val temperature: Double,
    val deviceMode: DeviceMode
) : Device() {

    companion object {
        const val MIN_TEMPERATURE = 7.0
        const val MAX_TEMPERATURE = 28.0
    }
}

data class RollerShutter(
    override val id: Long,
    override val name: String,
    val position: Int
) : Device() {

    companion object {
        const val MIN_POSITION = 0
        const val MAX_POSITION = 100
    }
}