package com.porcuon.modulotech.data.database.mapper

import com.porcuon.modulotech.data.database.entity.UserEntity
import com.porcuon.modulotech.domain.model.User
import java.util.Date

class UserEntityMapper(
    private val addressEntityMapper: AddressEntityMapper
) {

    fun map(userEntity: UserEntity?) = User(
        id = userEntity?.id ?: -1,
        firstName = userEntity?.firstName.orEmpty(),
        lastName = userEntity?.lastName.orEmpty(),
        address = addressEntityMapper.map(userEntity?.address),
        dateOfBirth = Date(userEntity?.dateOfBirth ?: 0)
    )

    fun map(user: User) = UserEntity(
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        address = addressEntityMapper.map(user.address),
        dateOfBirth = user.dateOfBirth.time
    )
}