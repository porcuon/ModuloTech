package com.porcuon.modulotech.domain.preferences

interface UserDevicesPreferences {

    fun isUserDevicesStoredInDatabase(): Boolean

    fun setUserDevicesStoredInDatabase()
}