package com.porcuon.modulotech.data.repository

import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.data.database.dao.UserDao
import com.porcuon.modulotech.data.entity.UserEntity
import com.porcuon.modulotech.data.mapper.UserMapper
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.domain.repository.UserRepository

class DefaultUserRepository(
    private val userDao: UserDao,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUser(): Result<User> {
        return try {
            val userEntity: UserEntity = userDao.getUser()

            Result.Success(userMapper.map(userEntity))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveUser(user: User) {
        val userEntity: UserEntity = userMapper.map(user)
        userDao.saveUser(userEntity)
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            userDao.updateUser(userMapper.map(user))

            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}