package com.porcuon.modulotech.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.porcuon.modulotech.data.database.entity.DeviceEntity
import com.porcuon.modulotech.domain.model.DeviceType

@Dao
interface DeviceDao {

    @Query("SELECT * FROM device WHERE type IN (:deviceTypes)")
    suspend fun getDevicesByTypes(deviceTypes: List<DeviceType>): List<DeviceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDevices(devices: List<DeviceEntity>)

    @Delete
    suspend fun deleteDevice(device: DeviceEntity)

    @Update
    suspend fun updateDevice(device: DeviceEntity)
}