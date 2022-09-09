package com.porcuon.modulotech

import androidx.room.Database
import androidx.room.RoomDatabase
import com.porcuon.modulotech.devices.DeviceDao
import com.porcuon.modulotech.devices.DeviceEntity
import com.porcuon.modulotech.profile.UserDao
import com.porcuon.modulotech.profile.UserEntity

@Database(entities = [DeviceEntity::class, UserEntity::class], version = 1)
abstract class ModuloTechDatabase : RoomDatabase() {

    abstract fun deviceDao(): DeviceDao

    abstract fun userDao(): UserDao
}