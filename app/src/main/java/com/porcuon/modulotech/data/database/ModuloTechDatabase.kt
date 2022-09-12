package com.porcuon.modulotech.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.porcuon.modulotech.data.database.dao.DeviceDao
import com.porcuon.modulotech.data.database.dao.UserDao
import com.porcuon.modulotech.data.entity.DeviceEntity
import com.porcuon.modulotech.data.entity.UserEntity

@Database(entities = [DeviceEntity::class, UserEntity::class], version = 1)
abstract class ModuloTechDatabase : RoomDatabase() {

    abstract fun deviceDao(): DeviceDao

    abstract fun userDao(): UserDao
}