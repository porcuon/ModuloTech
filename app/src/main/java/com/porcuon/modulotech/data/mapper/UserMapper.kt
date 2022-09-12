package com.porcuon.modulotech.data.mapper

import com.porcuon.modulotech.data.entity.UserEntity
import com.porcuon.modulotech.domain.model.User
import java.util.Date

private const val DEFAULT_DATE_MILLIS = 0L

class UserMapper(
    private val addressMapper: AddressMapper
) {

    fun map(userEntity: UserEntity?) = User(
        id = userEntity?.id ?: -1,
        firstName = userEntity?.firstName.orEmpty(),
        lastName = userEntity?.lastName.orEmpty(),
        address = addressMapper.map(userEntity?.address),
        dateOfBirth = Date(userEntity?.dateOfBirth ?: DEFAULT_DATE_MILLIS)
    )

    fun map(user: User) = UserEntity(
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        address = addressMapper.map(user.address),
        dateOfBirth = user.dateOfBirth.time
    )
}