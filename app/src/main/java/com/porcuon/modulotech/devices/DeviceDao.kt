package com.porcuon.modulotech.devices

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeviceDao {

    @Query("SELECT * FROM device WHERE productType IN (:productTypes)")
    fun getDevicesByProductTypes(productTypes: List<String>): List<DeviceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDevices(devices: List<DeviceEntity>)

    @Delete
    fun deleteDevice(device: DeviceEntity)
}