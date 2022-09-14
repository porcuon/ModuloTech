package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.data.database.dao.UserDao
import com.porcuon.modulotech.data.database.entity.UserEntity
import com.porcuon.modulotech.data.database.mapper.UserEntityMapper
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.domain.repository.UserRepository

class DefaultUserRepository(
    private val userDao: UserDao,
    private val userEntityMapper: UserEntityMapper
) : UserRepository {

    override suspend fun getUser(): Result<User> {
        return try {
            val userEntity: UserEntity = userDao.getUser()

            Result.Success(userEntityMapper.map(userEntity))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveUser(user: User) {
        val userEntity: UserEntity = userEntityMapper.map(user)
        userDao.saveUser(userEntity)
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            userDao.updateUser(userEntityMapper.map(user))

            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}