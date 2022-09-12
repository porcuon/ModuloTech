package com.porcuon.modulotech.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.porcuon.modulotech.domain.preferences.UserDevicesPreferences

private const val KEY_USER_DEVICES_PREFERENCES = "KEY_USER_DEVICES_PREFERENCES"
private const val IS_USER_DEVICES_STORED_IN_DATABASE = "IS_USER_DEVICES_STORED_IN_DATABASE"

class DefaultUserDevicesPreferences(
    context: Context
) : UserDevicesPreferences {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        KEY_USER_DEVICES_PREFERENCES,
        Context.MODE_PRIVATE
    )

    override fun isUserDevicesStoredInDatabase(): Boolean {
        return sharedPreferences.getBoolean(IS_USER_DEVICES_STORED_IN_DATABASE, false)
    }

    override fun setUserDevicesStoredInDatabase() {
        sharedPreferences
            .edit()
            .putBoolean(IS_USER_DEVICES_STORED_IN_DATABASE, true)
            .apply()
    }
}