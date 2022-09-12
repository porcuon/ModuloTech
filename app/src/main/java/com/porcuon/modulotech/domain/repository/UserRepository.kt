package com.porcuon.modulotech.domain.repository

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.model.User

interface UserRepository {

    suspend fun getUser(): Result<User>

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User): Result<User>
}