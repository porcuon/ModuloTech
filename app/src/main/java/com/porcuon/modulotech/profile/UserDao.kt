package com.porcuon.modulotech.profile

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): UserEntity

    @Update
    fun updateUser(user: UserEntity)
}